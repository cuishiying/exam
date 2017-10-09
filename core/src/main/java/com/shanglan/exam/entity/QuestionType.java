package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by cuishiying on 2017/6/13.
 * 试题类型（单选、多选、判断、填空、主观）
 */
@Entity
@Table(name = "cnoa_test_questiontype")
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
