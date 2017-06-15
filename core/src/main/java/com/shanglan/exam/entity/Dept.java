package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Created by cuishiying on 2017/6/15.
 * 部门
 */
@Entity
public class Dept extends BaseEntity{
    private static final long serialVersionUID = 2402560884815241740L;
    private String name;
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
