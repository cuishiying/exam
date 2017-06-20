package com.shanglan.exam.dto;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/20.
 * 用户提交的答案
 */
public class UserAnswers {
    private Integer questionId;
    private List<String> userAnswer;


    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<String> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(List<String> userAnswer) {
        this.userAnswer = userAnswer;
    }
}
