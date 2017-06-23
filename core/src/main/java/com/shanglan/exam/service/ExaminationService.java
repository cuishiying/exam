package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.dto.ExamRecordDTO;
import com.shanglan.exam.dto.UserAnswers;
import com.shanglan.exam.entity.*;
import com.shanglan.exam.repository.ExaminationRepository;
import com.shanglan.exam.repository.TestPaperRuleRepository;
import com.shanglan.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/19.
 */
@Service
@Transactional
public class ExaminationService {

    @Autowired
    private QuestionBankService questionBankService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestPaperRuleRepository testPaperRuleRepository;
    @Autowired
    private ExaminationRepository examinationRepository;
    @Autowired
    private QuestionCategoryService questionCategoryService;

    /**
     * 用户是否有正在进行的考试
     * @param empId
     * @return
     */
    public AjaxResponse isAttending(Integer empId) {
        //todo
        TestPaperRule qci = testPaperRuleRepository.findAll().get(0);

        if(LocalTime.now().isBefore(qci.getEffectiveStartDate())){
            //考试未开始
            return AjaxResponse.fail("考试未开始");
        }else if(LocalTime.now().isBefore(qci.getEffectiveEndDate())){
            //开始正在进行
            // TODO: 2017/6/23   分为没开始答题、正在答题
            ExamRecord examRecord = examinationRepository.findExamRecord("张三", LocalDate.now().toString());
            if(null!=examRecord){
                return AjaxResponse.fail("你已经参加了考试");
            }
            AjaxResponse ajaxResponse = questionBankService.generateQuestionList(qci);
            return ajaxResponse;
        }else{
            //开始已结束
            return AjaxResponse.fail("考试已结束");
        }
    }

    /**
     * 从缓存中提交答案
     * @param empId
     * @return
     */
    public AjaxResponse submitExamToCache(Integer empId) {
        return AjaxResponse.success();
    }

    /**
     * 验证考卷
     * @param accoutNumber
     * @return
     */
    public AjaxResponse validateExam(String accoutNumber,List<UserAnswers> userAnswers) {
        ExamRecord examRecord = examinationRepository.findExamRecord(accoutNumber, LocalDate.now().toString());
        if(null!=examRecord){
            return AjaxResponse.fail("你已经参加了考试");
        }

        return calculationScore(accoutNumber,userAnswers);
    }

    /**
     * 分页时缓存考试
     * @return
     */
    public AjaxResponse cacheExam(List<UserAnswers> userAnswers){

        return AjaxResponse.success();
    }

    /**
     * 获取考试成绩列表
     * @param pageable
     * @return
     */
    public Page<ExamRecord> findAll(Pageable pageable){
        Page<ExamRecord> page = examinationRepository.findAll(pageable);
        return page;
    }

    /**
     *计算考试得分
     * @return
     */
    public AjaxResponse calculationScore(String userId,List<UserAnswers> userAnswers){

        User user = userRepository.findOne(1);

        // TODO: 2017/6/23
        if(null==user){
            user = new User();
            user.setMobile("15135173514");
            user.setQq("123456");
            user.setQuestionCategory(questionCategoryService.findByName("信息科"));
            user.setUsername("张三");
            user.setTruename("张三");
        }

        int errScore = 0;
        int totalScore = 0;
        List<UserAnswers> errAnswers = new ArrayList<>();//返回错误试题集合
        for(UserAnswers ua:userAnswers){
            Question qt = questionBankService.findById(ua.getQuestionId());
            totalScore+=qt.getScore();
            List<String> correctAnswerList = Arrays.asList(qt.getCorrectAnswer().split(";"));//正确答案
            List<String> userAnswer = ua.getUserAnswer();
            for(String u:userAnswer){
                if(!correctAnswerList.contains(u)||userAnswer.size()!=correctAnswerList.size()){
                    errScore+=qt.getScore();
                    errAnswers.add(ua);
                    break;
                }
            }
        }
        DecimalFormat df=new DecimalFormat("0");
        String scoreStr = df.format((float) (totalScore - errScore) / totalScore*100);


        TestPaperRule tpr = testPaperRuleRepository.findByQuestionCategory(user.getQuestionCategory());


        //考试成绩
        ExamRecord examRecord = new ExamRecord();
        examRecord.setScore(Integer.parseInt(scoreStr));
        examRecord.setExamId(LocalDate.now().toString());
        examRecord.setName(user.getUsername());
        examRecord.setQuestionCategory(user.getQuestionCategory());
        examRecord.setExamTime(LocalDateTime.now());
        examRecord.setAbsence(false);
        examRecord.setTestPaperType(testPaperRuleRepository.findByQuestionCategory(user.getQuestionCategory()).getTestPaperType());
        examRecord.setPass(Integer.parseInt(scoreStr)>tpr.getPassScore()?true:false);
        examinationRepository.save(examRecord);

        ExamRecordDTO examRecordDTO = new ExamRecordDTO();
        examRecordDTO.setExamRecord(examRecord);
        examRecordDTO.setErrAnswers(errAnswers);

        return AjaxResponse.success(examRecordDTO);
    }


}
