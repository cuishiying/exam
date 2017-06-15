package com.shanglan.exam.repository;

import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.RandomTestPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by cuishiying on 2017/6/15.
 * 每日随机试卷
 */
public interface RandomTestPaperRepository extends JpaRepository<RandomTestPaper, Integer>,JpaSpecificationExecutor<RandomTestPaper> {
}
