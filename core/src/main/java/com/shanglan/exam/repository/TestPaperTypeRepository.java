package com.shanglan.exam.repository;

import com.shanglan.exam.entity.QuestionCompositionItem;
import com.shanglan.exam.entity.TestPaperType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by cuishiying on 2017/6/13.
 */
public interface TestPaperTypeRepository extends JpaRepository<TestPaperType, Integer>,JpaSpecificationExecutor<TestPaperType> {

    TestPaperType findByName(String name);

}
