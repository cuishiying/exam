package com.shanglan.exam.repository;

import com.shanglan.exam.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by cuishiying on 2017/6/13.
 */
public interface QuestionBankRepository extends JpaRepository<Question, Integer>,JpaSpecificationExecutor<Question> {

}