package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by cuishiying on 2017/6/13.
 */
@Entity
public class Answer extends BaseEntity{


    private static final long serialVersionUID = -764728170462458136L;
    private String titleId;
    private String answer;
    private int status;//1正确答案;0错误答案

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
