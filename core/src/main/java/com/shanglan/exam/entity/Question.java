package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/13.
 * 试题
 */
@Entity
@Table(name = "cnoa_test_question")
public class Question extends BaseEntity{
    private static final long serialVersionUID = -8878544828516170631L;

    private int qId;//题号

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private QuestionType questionType;//题型（单选、多选、填空、判断、主观）
    private String title;//题目
    @OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL, orphanRemoval=true)
    private List<Answer> answers;//答案
    private Float score;//分值
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private QuestionCategory questionCategory;//试题类目
    private LocalDateTime addtime;//添加时间
    private String correctAnswer;//正确答案

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    public LocalDateTime getAddtime() {
        return addtime;
    }

    public void setAddtime(LocalDateTime addtime) {
        this.addtime = addtime;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
