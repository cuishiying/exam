package com.shanglan.exam.repository;

import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/13.
 */
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Integer>,JpaSpecificationExecutor<QuestionCategory> {

    @Query("select q from QuestionCategory q where  q.name =?1 and q.fid >1")
    QuestionCategory findByName(String name);

    @Query("select q from QuestionCategory q where  q.fid >1")
    List<QuestionCategory> findAll();

}
