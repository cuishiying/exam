package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/13.
 * 试卷//todo
 */
@Entity
public class TestPaper extends BaseEntity {
    private static final long serialVersionUID = -5134520455541380101L;

    private String name;
    private LocalDateTime createdTime;
    private boolean enable;
    private Integer examDuration; //单位：分钟
    @OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL, orphanRemoval=true)
    private List<TestPaperRule> questionComposition;//组卷规则

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Integer getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(Integer examDuration) {
        this.examDuration = examDuration;
    }

    public List<TestPaperRule> getQuestionComposition() {
        return questionComposition;
    }

    public void setQuestionComposition(List<TestPaperRule> questionComposition) {
        this.questionComposition = questionComposition;
    }
}
