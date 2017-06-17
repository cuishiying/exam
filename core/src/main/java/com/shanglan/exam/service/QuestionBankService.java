package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.base.ExcelUtils;
import com.shanglan.exam.entity.Answer;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.repository.QuestionBankRepository;
import com.shanglan.exam.repository.QuestionCategoryRepository;
import com.shanglan.exam.repository.QuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
