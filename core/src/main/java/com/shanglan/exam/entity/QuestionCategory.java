package com.shanglan.exam.entity;

import com.shanglan.exam.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/15.
 * 试题类目（部门）
 */
@Entity
@Table(name = "cnoa_main_struct")
public class QuestionCategory extends BaseEntity{


    private static final long serialVersionUID = -5237030693999396489L;
    @Column(unique = false, nullable = false)
    private String name;// 类目名称

    private Integer fid;//组织机构层级
    private String path;
    private String about;
    private Integer order;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
