package com.shanglan.exam.repository;

import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/13.
 */
public interface QuestionBankRepository extends JpaRepository<Question, Integer>,JpaSpecificationExecutor<Question> {

    /**
     * 部门-题型数量
     * @param type
     * @param questionCategoryId
     * @return
     */
    @Query("select count(*) from Question q where q.questionType =?1 and q.questionCategory.id=?2")
    Long countByQuestionTypeAndQuestionCategory(QuestionType type, Integer questionCategoryId);

    /**
     * 根据部门-题型获取所有试题
     * @param type
     * @param questionCategoryId
     * @return
     */
    @Query("select q.id from Question q where  q.questionType =?1 and q.questionCategory.id=?2")
    List<Integer> findAllByQuestionTypeAndQuestionCategory(QuestionType type, Integer questionCategoryId);
}
