package com.shanglan.exam.repository;

import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by cuishiying on 2017/6/14.
 */
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Integer>,JpaSpecificationExecutor<QuestionType> {

    QuestionType findByValue(String value);
}
