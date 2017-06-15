package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Created by cuishiying on 2017/6/13.
 * 每日一考，试题按工种随机
 */
@Entity
public class RandomTestPaper extends BaseEntity {
    private static final long serialVersionUID = -5134520455541380101L;

    private String name;
    private LocalDateTime createdTime;
    private TestPaperType testPaperType;//试卷类型（练习题、考试题）
    private boolean enable;
    private Integer examDuration; //单位：分钟
    private Integer countOfQuestion;//随机题数量
    private Dept dept;//部门类型（工种）
    private LocalDateTime startTime;
    private LocalDateTime endTime;

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

    public TestPaperType getTestPaperType() {
        return testPaperType;
    }

    public void setTestPaperType(TestPaperType testPaperType) {
        this.testPaperType = testPaperType;
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

    public Integer getCountOfQuestion() {
        return countOfQuestion;
    }

    public void setCountOfQuestion(Integer countOfQuestion) {
        this.countOfQuestion = countOfQuestion;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
