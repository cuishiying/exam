package com.shanglan.exam.entity;


import com.shanglan.exam.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 组卷规则
 * @author Administrator
 *
 */
@Entity
public class QuestionCompositionItem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@ManyToOne(optional = false)
	private QuestionCategory questionCategory;//试题类目、工种
	private Integer countOfSingleChoice;//单选数量
	private Integer countOfMutipleChoice;//多选数量
	private LocalDate   effectiveStartDate;//有效起始时间
	private LocalDate 	effectiveEndDate;//有效结束时间
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	private TestPaperType testPaperType;//试卷类型

	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

	public Integer getCountOfSingleChoice() {
		return countOfSingleChoice;
	}

	public void setCountOfSingleChoice(Integer countOfSingleChoice) {
		this.countOfSingleChoice = countOfSingleChoice;
	}

	public Integer getCountOfMutipleChoice() {
		return countOfMutipleChoice;
	}

	public void setCountOfMutipleChoice(Integer countOfMutipleChoice) {
		this.countOfMutipleChoice = countOfMutipleChoice;
	}

	public LocalDate getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(LocalDate effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public LocalDate getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(LocalDate effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public TestPaperType getTestPaperType() {
		return testPaperType;
	}

	public void setTestPaperType(TestPaperType testPaperType) {
		this.testPaperType = testPaperType;
	}
}
