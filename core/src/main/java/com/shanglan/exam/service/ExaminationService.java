package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.ExamRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cuishiying on 2017/6/19.
 */
@Service
@Transactional
public class ExaminationService {

    /**
     * 用户是否有正在进行的考试
     * @param empId
     * @return
     */
    public AjaxResponse isAttending(Integer empId) {
        return AjaxResponse.success();
    }

    /**
     * 从缓存中提交答案
     * @param empId
     * @return
     */
    public AjaxResponse submitExamToCache(Integer empId) {
        return AjaxResponse.success();
    }

    /**
     * 验证考卷
     * @param empId
     * @return
     */
    private AjaxResponse validateExam(Integer empId) {
        return AjaxResponse.success();
    }

    /**
     * 分页时缓存考试
     * @return
     */
    public AjaxResponse cacheExam(){
        return AjaxResponse.success();
    }

    /**
     *计算考试得分
     * @return
     */
    private ExamRecord calculationScore(Object examPaperData){
        return null;
    }
}
