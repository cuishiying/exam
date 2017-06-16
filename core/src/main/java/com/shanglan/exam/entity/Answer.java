package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by cuishiying on 2017/6/13.
 */
@Entity
public class Answer extends BaseEntity{


    private static final long serialVersionUID = -764728170462458136L;

    private String content; // 答案内容
    private boolean correct; // 是否是正确答案

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
