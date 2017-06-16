package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/15.
 * 试题类目（部门）
 */
@Entity
public class QuestionCategory extends BaseEntity{


    private static final long serialVersionUID = 178277452268870741L;

    @Column(unique = true, nullable = false)
    private String name;// 类目名称
    private LocalDateTime createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
