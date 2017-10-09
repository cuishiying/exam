package com.shanglan.exam.entity;


import com.shanglan.exam.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 组卷规则
 * @author Administrator
 *
 */
@Entity
@Table(name = "cnoa_test_paperrule")
public class TestPaperRule extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@ManyToOne(optional = false)
	private QuestionCategory questionCategory;//试题类目、工种
	private Integer countOfSingleChoice;//单选数量
	private Integer countOfMutipleChoice;//多选数量
	private Integer countOfTorF;//判断数量
	private LocalTime effectiveStartDate;//有效起始时间
	private LocalTime 	effectiveEndDate;//有效结束时间
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private TestPaperType testPaperType;//试卷类型
	private Integer passScore;//及格线
	private Integer examDuration; //单位：分钟

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

	public Integer getCountOfTorF() {
		return countOfTorF;
	}

	public void setCountOfTorF(Integer countOfTorF) {
		this.countOfTorF = countOfTorF;
	}

	public LocalTime getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(LocalTime effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public LocalTime getEffectiveEndDate() {
		return effectiveEndDate;
	}

	public void setEffectiveEndDate(LocalTime effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	public TestPaperType getTestPaperType() {
		return testPaperType;
	}

	public void setTestPaperType(TestPaperType testPaperType) {
		this.testPaperType = testPaperType;
	}

	public Integer getPassScore() {
		return passScore;
	}

	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}

	public Integer getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(Integer examDuration) {
		this.examDuration = examDuration;
	}
}
