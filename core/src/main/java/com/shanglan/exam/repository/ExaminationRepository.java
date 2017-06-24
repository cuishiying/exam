package com.shanglan.exam.repository;

import com.shanglan.exam.entity.ExamRecord;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by cuishiying on 2017/6/21.
 */
public interface ExaminationRepository  extends JpaRepository<ExamRecord, Integer>,JpaSpecificationExecutor<ExamRecord> {

    @Query("select e from ExamRecord e where e.uid =?1 and e.examId=?2")
    ExamRecord findExamRecord(Integer uid, String examId);
}
