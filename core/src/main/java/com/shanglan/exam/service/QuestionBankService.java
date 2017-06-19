package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.base.ExcelUtils;
import com.shanglan.exam.entity.Answer;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionCompositionItem;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.repository.QuestionBankRepository;
import com.shanglan.exam.repository.QuestionCategoryRepository;
import com.shanglan.exam.repository.QuestionTypeRepository;
import com.shanglan.exam.repository.TestPaperRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cuishiying on 2017/6/13.
 */
@Service
@Transactional
public class QuestionBankService {

    @Autowired
    private QuestionBankRepository questionBankRepository;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    private QuestionCategoryRepository questionCategoryRepository;

    @Autowired
    private TestPaperRuleRepository testPaperRuleRepository;

    /**
     * 批量导入试题
     * @param in
     * @param file
     * @return
     * @throws Exception
     */
    public AjaxResponse importExcel(InputStream in, MultipartFile file) throws Exception {
        List<List<Object>> listob = ExcelUtils.getBankListByExcel(in,file.getOriginalFilename());
        List<Question> questions=new ArrayList<Question>();

        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Question question = new Question();
            question.setTitle(String.valueOf(lo.get(0)));//题目
            question.setQuestionType(questionTypeRepository.findByValue(String.valueOf(lo.get(1))));//题型
            question.setAnswers(handleAnswer(String.valueOf(lo.get(2)),String.valueOf(lo.get(3))));//答案选项
            question.setCorrectAnswer(String.valueOf(lo.get(3)));//正确答案
            question.setScore(Integer.parseInt(String.valueOf(lo.get(4))));
            question.setQuestionCategory(questionCategoryRepository.findByName(String.valueOf(lo.get(5))));
            question.setAddtime(LocalDateTime.now());
            questions.add(question);
        }
        questionBankRepository.save(questions);
        return AjaxResponse.success();
    }

    /**
     * 添加试题
     * @param question
     * @return
     */
    public AjaxResponse addQuestion(Question question){
        question.setAddtime(LocalDateTime.now());
        question.setQuestionCategory(questionCategoryRepository.findByName(question.getQuestionCategory().getName()));
        question.setQuestionType(questionTypeRepository.findByValue(question.getQuestionType().getValue()));
        questionBankRepository.save(question);
        return AjaxResponse.success();
    }

    /**
     * 删除试题
     * @param id
     * @return
     */
    public AjaxResponse deleteQuestion(int id){
        questionBankRepository.delete(id);
        return AjaxResponse.success();
    }

    /**
     * 获取题库
     * @param pageable
     * @return
     */
    public Page<Question> getQuestionBank(Pageable pageable){
        Page<Question> page = questionBankRepository.findAll(pageable);
        return page;
    }

    /**
     * 根据id获取试题
     * @param id
     * @return
     */
    public Question findById(int id){
        Question question = questionBankRepository.findOne(id);
        return question;
    }

    /**
     * 组答案
     * @param answers
     * @param correctAnswer
     * @return
     */
    private  List<Answer> handleAnswer(String answers,String correctAnswer){

        List<Answer> answerList = new ArrayList();
        String[] items = answers.split(";");
        for (String item:items) {
            Answer answer = new Answer();
            answer.setKeyTag(item.substring(0,1));
            answer.setContent(item.substring(1));
            answer.setCorrect(item.substring(0,1).equals(correctAnswer)?true:false);
            answerList.add(answer);
        }
        return answerList;
    }

    /**
     * 从试卷类目中随机选择试题
     * @return
     */
    public List<Question> generateQuestionList(QuestionCompositionItem qci1){
        QuestionCompositionItem qci = testPaperRuleRepository.findAll().get(0);
        //获得单选多选总数
        long oneCount = questionBankRepository.countByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("单选题"), qci.getQuestionCategory().getId());
        long moreCount = questionBankRepository.countByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("多选题"), qci.getQuestionCategory().getId());
        if (qci.getCountOfSingleChoice() > oneCount || qci.getCountOfMutipleChoice() > moreCount) {
            throw new RuntimeException("数据错误，类目所需试题数大于类目总试题数");
        }

        List<Integer> questionId1 = questionBankRepository.findAllByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("单选题"), qci.getQuestionCategory().getId());
        List<Integer> oneIds = this.randomList(questionId1, qci.getCountOfSingleChoice());

        List<Integer> questionId2 = questionBankRepository.findAllByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("多选题"), qci.getQuestionCategory().getId());
        List<Integer> moreIds = this.randomList(questionId1, qci.getCountOfSingleChoice());

        List<Question> singleChoiceList = questionBankRepository.findAll(oneIds);
        List<Question> mutipleChoiceList = questionBankRepository.findAll(moreIds);
        return singleChoiceList;
    }

    /**
     * 从list中随机取出size个元素
     * @param list 源列表
     * @param size 取出个数
     * @return
     */
    private <T> List<T> randomList(List<T> list, int size) {
        if (CollectionUtils.isEmpty(list) || size < 1 || size > list.size()) return new ArrayList<>();
        List<T> result = new ArrayList<>(size);
        Random random = new Random();
        while (result.size() < size) {
            result.add(list.remove(random.nextInt(list.size())));
        }
        return result;
    }
}
