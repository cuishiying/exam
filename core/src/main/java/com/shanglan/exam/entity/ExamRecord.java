package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by cuishiying on 2017/6/16.
 * 成绩
 */
@Entity
@Table(name = "cnoa_test_record")
public class ExamRecord extends BaseEntity {

    private static final long serialVersionUID = 522708612562745572L;

    private Integer uid;//用户唯一id
    private String examId; //考试id，唯一，避免多次考试
    private String accoutNumber;//帐号
    private String name;//姓名
    private String employeeId;//工号

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private QuestionCategory questionCategory;//类目

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private TestPaperType testPaperType;//试卷类型
    private Integer score;//得分
    private LocalDateTime examTime;//考试时间
    private boolean absence;//缺席，true缺席，false未缺席
    private boolean pass;//缺席，true缺席，false未缺席

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getAccoutNumber() {
        return accoutNumber;
    }

    public void setAccoutNumber(String accoutNumber) {
        this.accoutNumber = accoutNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    public TestPaperType getTestPaperType() {
        return testPaperType;
    }

    public void setTestPaperType(TestPaperType testPaperType) {
        this.testPaperType = testPaperType;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getExamTime() {
        return examTime;
    }

    public void setExamTime(LocalDateTime examTime) {
        this.examTime = examTime;
    }

    public boolean isAbsence() {
        return absence;
    }

    public void setAbsence(boolean absence) {
        this.absence = absence;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
