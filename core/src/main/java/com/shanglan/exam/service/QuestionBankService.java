package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.base.ExcelUtils;
import com.shanglan.exam.dto.QueryDTO;
import com.shanglan.exam.dto.QuestionDTO;
import com.shanglan.exam.dto.QuestionModel;
import com.shanglan.exam.entity.*;
import com.shanglan.exam.repository.QuestionBankRepository;
import com.shanglan.exam.repository.QuestionCategoryRepository;
import com.shanglan.exam.repository.QuestionTypeRepository;
import com.shanglan.exam.repository.TestPaperRuleRepository;
import com.shanglan.exam.utils.JavaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private Environment env;

    /**
     * 批量导入试题
     * @param in
     * @param file
     * @return
     * @throws Exception
     * todo
     */
    public AjaxResponse importExcel(InputStream in, MultipartFile file) throws Exception {

        initQuestionType();

        List<List<Object>> listob = ExcelUtils.getBankListByExcel(in,file.getOriginalFilename());
        List<Question> questions=new ArrayList<Question>();

        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Question question = new Question();
            question.setTitle(String.valueOf(lo.get(0)));//题目
            question.setQuestionType(questionTypeRepository.findByValue(String.valueOf(lo.get(1))));//题型
            //如果单选-答案项不能超过两个
            if(questionTypeRepository.findByValue(String.valueOf(lo.get(1))).getValue().equals("单选题")&& JavaUtils.replaceBlank(String.valueOf(lo.get(3))).length()>1){
                String log = String.valueOf(lo.get(3)).trim();
                Integer count = log.length();
                return AjaxResponse.fail("第"+(i+1)+"行数据单选题正确答案不能为多个");
            }
            if(questionTypeRepository.findByValue(String.valueOf(lo.get(1))).getValue().equals("判断题")&&String.valueOf(lo.get(3)).trim().length()>1){
                String log = String.valueOf(lo.get(3)).trim();
                Integer count = log.length();
                return AjaxResponse.fail("第"+(i+1)+"行数据判断题只有一个正确答案");
            }
            question.setAnswers(handleAnswer(String.valueOf(lo.get(2)),String.valueOf(lo.get(3))));//答案选项
            question.setCorrectAnswer(String.valueOf(lo.get(3)));//正确答案
            question.setScore(Float.parseFloat(StringUtils.isEmpty(String.valueOf(lo.get(4)))?"1":String.valueOf(lo.get(4))));
            // TODO: 2017/6/24 这里需要确认，是否部门不存在时不录入并提醒
            if(null==questionCategoryRepository.findByName(String.valueOf(lo.get(5)))){
//                QuestionCategory questionCategory = new QuestionCategory();
//                questionCategory.setName(String.valueOf(lo.get(5)));
//                questionCategoryRepository.save(questionCategory);
            }
            question.setQuestionCategory(questionCategoryRepository.findByName(String.valueOf(lo.get(5))));
            question.setAddtime(LocalDateTime.now());
            questions.add(question);
        }
        questionBankRepository.save(questions);
        return AjaxResponse.success();
    }

    /**
     * 导出试题模板
     * @param response
     */
    public void exportQuestions(HttpServletResponse response){
        String fileName = "试题模板";
        String sheetName = "试题模板";
        List<QuestionModel> list = new ArrayList<>();
        QuestionModel question = new QuestionModel();
        question.setTitle("危险是指系统发生不期望后果的可能性超过了_   __。");
        question.setQuestionType("多选题");
        question.setAnswers("A安全性要求$B可预防的范围$C人们的承受程度");
        question.setCorrectAnswer("B,C");
        question.setScore(Float.parseFloat("1"));
        question.setQuestionCategory("安全监察科");
        list.add(question);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("题目","title");
        map.put("题型","questionType");
        map.put("选项","answers");
        map.put("答案","correctAnswer");
        map.put("分值","score");
        map.put("部门","questionCategory");
        ExcelUtils.export(fileName,sheetName,QuestionModel.class,list,map,response);
    }

    /**
     * 初始化数据库题型数据
     */
    private void initQuestionType() {
        if(questionTypeRepository.findAll().size()==0){
            List<String> initArr = new ArrayList(Arrays.asList("单选题", "多选题","判断题"));
            List<QuestionType> qtList = new ArrayList<>();
            for(String str:initArr){
                QuestionType qt = new QuestionType();
                qt.setValue(str);
                qtList.add(qt);
            }
            questionTypeRepository.save(qtList);
        }
    }

    /**
     * 单个添加试题
     * @param question
     * @return
     */
    public AjaxResponse addQuestion(Question question){
        if(question.getQuestionType().getValue().equals("单选题")&&question.getCorrectAnswer().length()>1){
            return AjaxResponse.fail("单选题正确答案不能为多个");
        }
        if(question.getQuestionType().getValue().equals("判断题")&&question.getCorrectAnswer().length()>1){
            return AjaxResponse.fail("判断题只有一个正确答案");
        }
        question.setAddtime(LocalDateTime.now());
        question.setQuestionCategory(questionCategoryRepository.findByName(question.getQuestionCategory().getName()));
        question.setQuestionType(questionTypeRepository.findByValue(question.getQuestionType().getValue()));
        questionBankRepository.save(question);
        return AjaxResponse.success();
    }

    /**
     * 修改试题
     * @param q
     * @return
     */
    public AjaxResponse editQuestion(Integer id,Question q){
        if(q.getQuestionType().getValue()=="单选题"&&q.getCorrectAnswer().length()>1){
            return AjaxResponse.fail("单选题答案不能为多个");
        }
        if(q.getQuestionType().getValue()=="判断题"&&q.getCorrectAnswer().length()>1){
            return AjaxResponse.fail("判断题只有一个正确答案");
        }
        Question question = questionBankRepository.findOne(id);
        question.setQuestionCategory(questionCategoryRepository.findByName(q.getQuestionCategory().getName()));
        question.setQuestionType(questionTypeRepository.findByValue(q.getQuestionType().getValue()));
        question.setCorrectAnswer(q.getCorrectAnswer());
        question.getAnswers().clear();
        question.getAnswers().addAll(q.getAnswers());
        question.setScore(q.getScore());
        question.setTitle(q.getTitle());
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
    public Page<Question> getQuestionBank(String keyword,Pageable pageable){
        if(keyword==null){
            keyword = "";
        }
        Page<Question> page = questionBankRepository.findAll("%"+keyword+"%", pageable);
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

    public Page<Question> findAll(Pageable pageable){
        Page<Question> all = questionBankRepository.findAll(pageable);
        return all;
    }
    public Page<Question> findAll(String keyword,Pageable pageable){
        Page<Question> all = questionBankRepository.findAll("%"+keyword+"%",pageable);
        return all;
    }

    /**
     * 组答案
     * @param answers
     * @param correctAnswer
     * @return
     */
    private  List<Answer> handleAnswer(String answers,String correctAnswer){

        String split = env.getProperty("excel.import.split");
        List<Answer> answerList = new ArrayList();
        String[] items = answers.split(split);
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
    public AjaxResponse generateQuestionList(TestPaperRule qci){

        //获得单选多选总数
        long oneCount = questionBankRepository.countByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("单选题"), qci.getQuestionCategory().getId());
        long moreCount = questionBankRepository.countByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("多选题"), qci.getQuestionCategory().getId());
        long torfCount = questionBankRepository.countByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("判断题"), qci.getQuestionCategory().getId());
        long normalCount = questionBankRepository.countByNormal();
        if (qci.getCountOfSingleChoice() > oneCount+normalCount || qci.getCountOfMutipleChoice() > moreCount+normalCount || qci.getCountOfTorF()>torfCount+normalCount) {
//            throw new RuntimeException("数据错误，类目所需试题数大于类目总试题数");
            return AjaxResponse.fail("数据错误，类目所需试题数大于类目总试题数,请重新设置考试规则");
        }

        //通用题
        List<Integer> normalIds1 = questionBankRepository.findAllNormalQUestion(questionTypeRepository.findByValue("单选题"));
        List<Integer> normalIds2 = questionBankRepository.findAllNormalQUestion(questionTypeRepository.findByValue("多选题"));
        List<Integer> normalIds3 = questionBankRepository.findAllNormalQUestion(questionTypeRepository.findByValue("判断题"));

        //随机出单选题
        List<Integer> questionId1 = questionBankRepository.findAllByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("单选题"), qci.getQuestionCategory().getId());
        questionId1.addAll(normalIds1);
        List<Integer> oneIds = this.randomList(questionId1, qci.getCountOfSingleChoice());
        List<Question> singleChoiceList = questionBankRepository.findAll(oneIds);

        //随机出多选题
        List<Integer> questionId2 = questionBankRepository.findAllByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("多选题"), qci.getQuestionCategory().getId());
        questionId2.addAll(normalIds2);
        List<Integer> moreIds = this.randomList(questionId2, qci.getCountOfMutipleChoice());
        List<Question> mutipleChoiceList = questionBankRepository.findAll(moreIds);

        //随机出判断题
        List<Integer> questionId3 = questionBankRepository.findAllByQuestionTypeAndQuestionCategory(questionTypeRepository.findByValue("判断题"), qci.getQuestionCategory().getId());
        questionId3.addAll(normalIds3);
        List<Integer> torfIds = this.randomList(questionId3, qci.getCountOfTorF());
        List<Question> torfList = questionBankRepository.findAll(torfIds);

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setSingleChoiceList(singleChoiceList);
        questionDTO.setMutipleChoiceList(mutipleChoiceList);
        questionDTO.setTorfList(torfList);
        questionDTO.setShowSingleChoice(singleChoiceList.size()>0);
        questionDTO.setShowMutipleChoice(mutipleChoiceList.size()>0);
        questionDTO.setShowTorf(torfList.size()>0);
        return AjaxResponse.success(questionDTO);
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

    /**
     * 多条件查询
     * @param queryVo
     * @return
     */
    private Specification<Question> getWhereClause(QueryDTO queryVo){
        return (root, query, cb) -> {
            List<Predicate> predicate = new ArrayList<>();

            //关键词
            if(queryVo!=null&& StringUtils.isNotBlank(queryVo.getKeyword())){
                predicate.add(cb.or(cb.like(root.<String>get("title"), "%" + queryVo.getKeyword().trim() + "%"),
                        cb.like(root.<String>get("correctAnswer"), "%" + queryVo.getKeyword().trim() + "%")));
            }
            //题型
            if(queryVo!=null&&queryVo.getQuestionType()!=null){
                Predicate ownerQuery = cb.equal(root.<Integer>get("questionType"), queryVo.getQuestionType());
                predicate.add(ownerQuery);
            }
            return query.where(predicate.toArray(new Predicate[predicate.size()])).getRestriction();
        };
    }
}
