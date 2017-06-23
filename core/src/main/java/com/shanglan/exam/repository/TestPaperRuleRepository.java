package com.shanglan.exam.repository;

import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.entity.TestPaperRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by cuishiying on 2017/6/13.
 */
public interface TestPaperRuleRepository extends JpaRepository<TestPaperRule, Integer>,JpaSpecificationExecutor<TestPaperRule> {

    TestPaperRule findByQuestionCategory(QuestionCategory questionCategory);

}
