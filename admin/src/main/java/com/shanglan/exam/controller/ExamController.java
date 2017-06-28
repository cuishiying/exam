package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.service.ExaminationService;
import com.shanglan.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cuishiying on 2017/6/28.
 */
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExaminationService examinationService;

    /**
     * 考卷列表
     * @return
     */
    @RequestMapping
    public ModelAndView examPaper(HttpServletRequest request){
        ModelAndView model = null;
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        AjaxResponse ajaxResponse = examinationService.isAttending(uid);
        if(ajaxResponse.isSuccess()){
            model = new ModelAndView("exam_question");
            model.addObject("questions",ajaxResponse.getData());
        }else{
            model = new ModelAndView("exam_status");
            model.addObject("message",ajaxResponse.getMessage());
        }
        return model;
    }

}
