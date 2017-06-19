package com.shanglan.exam.dto;

import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.entity.QuestionType;

/**
 * Created by cuishiying on 2017/6/19.
 */
public class QueryDTO {
    private String keyword;
    private QuestionCategory questionCategory;
    private QuestionType questionType;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}
