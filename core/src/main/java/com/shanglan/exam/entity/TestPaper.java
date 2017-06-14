package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by cuishiying on 2017/6/13.
 */
@Entity
public class TestPaper extends BaseEntity {
    private static final long serialVersionUID = -5134520455541380101L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
