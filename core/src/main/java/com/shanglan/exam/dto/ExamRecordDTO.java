package com.shanglan.exam.dto;

import com.shanglan.exam.entity.ExamRecord;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/21.
 */
public class ExamRecordDTO {
    private ExamRecord examRecord;
    private List<UserAnswers> errAnswers;

    public ExamRecord getExamRecord() {
        return examRecord;
    }

    public void setExamRecord(ExamRecord examRecord) {
        this.examRecord = examRecord;
    }

    public List<UserAnswers> getErrAnswers() {
        return errAnswers;
    }

    public void setErrAnswers(List<UserAnswers> errAnswers) {
        this.errAnswers = errAnswers;
    }
}
