package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by cuishiying on 2017/6/15.
 * 试卷类型（每日一考、试卷、练习）
 */
@Entity
@Table(name = "cnoa_test_papertype")
public class TestPaperType extends BaseEntity{

    private static final long serialVersionUID = -8771391100100509322L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
