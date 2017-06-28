package com.shanglan.exam.dto;

import com.shanglan.exam.entity.Question;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/20.
 * 用户随机获取到的试题
 */
public class QuestionDTO {
    private List<Question> singleChoiceList;
    private List<Question> mutipleChoiceList;
    private List<Question> torfList;

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

    public List<Question> getTorfList() {
        return torfList;
    }

    public void setTorfList(List<Question> torfList) {
        this.torfList = torfList;
    }
}
