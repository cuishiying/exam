package com.shanglan.exam.dto;

import com.shanglan.exam.entity.Question;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/20.
 */
public class QuestionDTO {
    private List<Question> singleChoiceList;
    private List<Question> mutipleChoiceList;

    public List<Question> getSingleChoiceList() {
        return singleChoiceList;
    }

    public void setSingleChoiceList(List<Question> singleChoiceList) {
        this.singleChoiceList = singleChoiceList;
    }

    public List<Question> getMutipleChoiceList() {
        return mutipleChoiceList;
    }

    public void setMutipleChoiceList(List<Question> mutipleChoiceList) {
        this.mutipleChoiceList = mutipleChoiceList;
    }
}
