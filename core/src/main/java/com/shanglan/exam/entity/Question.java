package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by cuishiying on 2017/6/13.
 */
@Entity
public class Question extends BaseEntity{
    private static final long serialVersionUID = -8878544828516170631L;

    private int typeId;//题型（单选、多选、填空、判断、主观）
    private int paperId;//试卷
    private int qId;//题号
    private int score;//分值
    private String title;//题干
    private String addtime;//添加时间
    private String workType;//工种


    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
}
