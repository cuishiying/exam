package com.shanglan.exam.dto;

import com.shanglan.exam.entity.QuestionCategory;

/**
 * Created by cuishiying on 2017/6/27.
 * 考试成绩统计
 */
public class StockItemDTO implements Comparable<StockItemDTO>{
    private Double passRate;//及格率
    private QuestionCategory questionCategory;//部门

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }



    @Override
    public int compareTo(StockItemDTO o) {
        return o.getPassRate().compareTo(this.passRate);
    }
}
