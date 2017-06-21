package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/13.
 * 试卷//todo
 */
@Entity
public class User extends BaseEntity {

    private static final long serialVersionUID = -2301740839588119761L;
    private String username;
    private String truename;
    private String mobile;
    private String workphone;
    private String qq;
    @ManyToOne(optional = false)
    private QuestionCategory questionCategory;//试题类目、工种

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }
}
