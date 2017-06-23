package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.dto.QuestionDTO;
import com.shanglan.exam.dto.UserAnswers;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.service.ExaminationService;
import com.shanglan.exam.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by cuishiying on 2017/6/15.
 */
@RestController
@RequestMapping("/")
public class ExaminationController {

    @Autowired
    private QuestionBankService questionBankService;
    @Autowired
    private ExaminationService examinationService;

    /**
     * 考卷列表
     * @param pageable
     * @return
     */
    @RequestMapping
    public ModelAndView examPaper(Pageable pageable){
        ModelAndView model = null;
        AjaxResponse ajaxResponse = examinationService.isAttending(0);
        if(ajaxResponse.isSuccess()){
            model = new ModelAndView("index");
            model.addObject("questions",ajaxResponse.getData());
        }else{
            model = new ModelAndView("exam_status");
            model.addObject("message",ajaxResponse.getMessage());
        }
        return model;
    }

    /**
     * 提交考卷
     * @param userAnswers
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/examination/submit", method = RequestMethod.POST)
    public AjaxResponse submitExamPage(@RequestBody List<UserAnswers> userAnswers,HttpServletRequest request) {
        AjaxResponse result = examinationService.validateExam("张三",userAnswers);
        return result;
    }

}
