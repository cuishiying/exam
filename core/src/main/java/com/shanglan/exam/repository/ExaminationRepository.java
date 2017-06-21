package com.shanglan.exam.repository;

import com.shanglan.exam.entity.ExamRecord;
import com.shanglan.exam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by cuishiying on 2017/6/21.
 */
public interface ExaminationRepository  extends JpaRepository<ExamRecord, Integer>,JpaSpecificationExecutor<ExamRecord> {
}
