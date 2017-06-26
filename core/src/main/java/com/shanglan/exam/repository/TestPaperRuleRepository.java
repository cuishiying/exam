package com.shanglan.exam.repository;

import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.entity.TestPaperRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/13.
 */
public interface TestPaperRuleRepository extends JpaRepository<TestPaperRule, Integer>,JpaSpecificationExecutor<TestPaperRule> {

    @Query("select t from TestPaperRule t where t.questionCategory.id=?1")
    TestPaperRule findByQuestionCategory(Integer questionCategoryId);

    TestPaperRule findByQuestionCategory(QuestionCategory questionCategory);

    List<TestPaperRule> findAll();

}
