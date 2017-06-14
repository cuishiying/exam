package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Entity;

/**
 * Created by cuishiying on 2017/6/13.
 */
@Entity
public class QuestionType extends BaseEntity{

    private static final long serialVersionUID = -7009699985575383529L;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
