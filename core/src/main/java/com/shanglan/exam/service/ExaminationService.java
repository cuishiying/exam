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
    private UserService userService;
    @Autowired
    private TestPaperRulesService testPaperRulesService;
    @Autowired
    private ExaminationRepository examinationRepository;
    @Autowired
    private QuestionCategoryService questionCategoryService;

    /**
     * 用户是否有正在进行的考试
     * @param uid
     * @return
     */
    public AjaxResponse isAttending(Integer uid) {

        if(null==uid){
            return AjaxResponse.fail("当前未登录");
        }

        // TODO: 2017/6/24   这里需要对接
        User user = userService.findByUid(uid);
        Integer deptId = user.getDeptId();
        QuestionCategory category = questionCategoryService.findByid(deptId);
        TestPaperRule qci = testPaperRulesService.findByQuestionCategory(category);

        if(null==qci){
            return AjaxResponse.fail("信息有误");
        }
        if(LocalTime.now().isBefore(qci.getEffectiveStartDate())){
            //考试未开始
            return AjaxResponse.fail("考试未开始");
        }else if(LocalTime.now().isBefore(qci.getEffectiveEndDate())){
            //开始正在进行
            // TODO: 2017/6/23   分为没开始答题、正在答题,需要加缓存,后期加
            ExamRecord examRecord = examinationRepository.findExamRecord(uid, LocalDate.now().toString());
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
     * @param uid
     * @return
     */
    public AjaxResponse submitExamToCache(Integer uid) {
        return AjaxResponse.success();
    }

    /**
     * 验证考卷，是否已经参加考试
     * @param uid
     * @return
     */
    public AjaxResponse validateExam(Integer uid,List<UserAnswers> userAnswers) {
        ExamRecord examRecord = examinationRepository.findExamRecord(uid, LocalDate.now().toString());
        if(null!=examRecord){
            return AjaxResponse.fail("你已经参加了考试");
        }

        return calculationScore(uid,userAnswers);
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
    public AjaxResponse calculationScore(Integer uid,List<UserAnswers> userAnswers){

        User user = userService.findByUid(uid);

        if(null==user){
            return AjaxResponse.fail("帐号不存在");
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


        TestPaperRule tpr = testPaperRulesService.findByQuestionCategory(user.getDeptId());


        //考试成绩
        ExamRecord examRecord = new ExamRecord();
        examRecord.setScore(Integer.parseInt(scoreStr));
        examRecord.setExamId(LocalDate.now().toString());
        examRecord.setName(user.getUsername());
        examRecord.setQuestionCategory(questionCategoryService.findByid(user.getDeptId()));
        examRecord.setExamTime(LocalDateTime.now());
        examRecord.setAbsence(false);
        examRecord.setTestPaperType(testPaperRulesService.findByQuestionCategory(user.getDeptId()).getTestPaperType());
        examRecord.setPass(Integer.parseInt(scoreStr)>tpr.getPassScore()?true:false);
        examinationRepository.save(examRecord);

        ExamRecordDTO examRecordDTO = new ExamRecordDTO();
        examRecordDTO.setExamRecord(examRecord);
        examRecordDTO.setErrAnswers(errAnswers);

        return AjaxResponse.success(examRecordDTO);
    }


}
