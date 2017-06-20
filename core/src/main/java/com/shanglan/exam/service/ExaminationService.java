package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.dto.UserAnswers;
import com.shanglan.exam.entity.Answer;
import com.shanglan.exam.entity.ExamRecord;
import com.shanglan.exam.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
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

    /**
     * 用户是否有正在进行的考试
     * @param empId
     * @return
     */
    public AjaxResponse isAttending(Integer empId) {
        return AjaxResponse.success();
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
     * @param empId
     * @return
     */
    private AjaxResponse validateExam(Integer empId) {
        return AjaxResponse.success();
    }

    /**
     * 分页时缓存考试
     * @return
     */
    public AjaxResponse cacheExam(List<UserAnswers> userAnswers){

        return AjaxResponse.success();
    }

    /**
     *计算考试得分
     * @return
     */
    public AjaxResponse calculationScore(List<UserAnswers> userAnswers){
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
        ExamRecord examRecord = new ExamRecord();
        examRecord.setScore(Integer.parseInt(scoreStr));

        return AjaxResponse.success(errAnswers);
    }


}
