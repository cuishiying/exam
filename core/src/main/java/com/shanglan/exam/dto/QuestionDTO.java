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
    private boolean showSingleChoice;
    private boolean showMutipleChoice;
    private boolean showTorf;


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

    public boolean isShowSingleChoice() {
        return showSingleChoice;
    }

    public void setShowSingleChoice(boolean showSingleChoice) {
        this.showSingleChoice = showSingleChoice;
    }

    public boolean isShowMutipleChoice() {
        return showMutipleChoice;
    }

    public void setShowMutipleChoice(boolean showMutipleChoice) {
        this.showMutipleChoice = showMutipleChoice;
    }

    public boolean isShowTorf() {
        return showTorf;
    }

    public void setShowTorf(boolean showTorf) {
        this.showTorf = showTorf;
    }
}
