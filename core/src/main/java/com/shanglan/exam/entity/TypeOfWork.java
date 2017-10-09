package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by cuishiying on 2017/6/15.
 * 工种
 */
@Entity
@Table(name = "cnoa_user_worktype")
public class TypeOfWork extends BaseEntity{


    private static final long serialVersionUID = -5237030693999396489L;
    @Column(unique = false, nullable = false)
    private String name;// 类目名称



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
