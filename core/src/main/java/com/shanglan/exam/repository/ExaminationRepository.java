package com.shanglan.exam.repository;

import com.shanglan.exam.entity.ExamRecord;
import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

/**
 * Created by cuishiying on 2017/6/21.
 */
public interface ExaminationRepository  extends JpaRepository<ExamRecord, Integer>,JpaSpecificationExecutor<ExamRecord> {

    @Query("select e from ExamRecord e where e.uid =?1 and e.examId=?2")
    ExamRecord findExamRecord(Integer uid, String examId);

    @Query("select e from ExamRecord e where e.uid=?1")
    Page<ExamRecord> findAll(Integer uid,Pageable pageable);

    @Query("select count(1) from ExamRecord e where e.questionCategory.id=?1")
    Long findTotalCountByCategory(Integer questionCategoryId);

    @Query("select count(1) from ExamRecord e where e.questionCategory.id=?1 and e.pass=true")
    Long findPassCountByCategory(Integer questionCategoryId);

    @Query("select count(1) from ExamRecord e where e.questionCategory.id=?1 and e.examTime>?2 and e.examTime<?3")
    Long findTotalCountByCategory(Integer questionCategoryId, LocalDateTime startTime,LocalDateTime endTime);

    @Query("select count(1) from ExamRecord e where e.questionCategory.id=?1 and e.pass=true and e.examTime>?2 and e.examTime<?3")
    Long findPassCountByCategory(Integer questionCategoryId, LocalDateTime startTime,LocalDateTime endTime);


}
