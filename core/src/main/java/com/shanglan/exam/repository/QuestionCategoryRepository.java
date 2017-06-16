package com.shanglan.exam.repository;

import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by cuishiying on 2017/6/13.
 */
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Integer>,JpaSpecificationExecutor<QuestionCategory> {

    QuestionCategory findByName(String name);
}
