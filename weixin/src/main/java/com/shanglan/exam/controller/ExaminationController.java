package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.dto.QuestionDTO;
import com.shanglan.exam.dto.UserAnswers;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.User;
import com.shanglan.exam.service.ExaminationService;
import com.shanglan.exam.service.QuestionBankService;
import com.shanglan.exam.service.UserService;
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
    @Autowired
    private UserService userService;

    /**
     * 考卷列表
     * @return
     */
    @RequestMapping
    public ModelAndView examPaper(String username,String truename,HttpServletRequest request){
        User user = userService.findUserByUsernameAndtruename(username, truename);
        if(null!=user){
            request.getSession().invalidate();
            request.getSession().setAttribute("uid", user.getUid());
        }
        ModelAndView model = null;
        AjaxResponse ajaxResponse = examinationService.isAttending(user.getUid());
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
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        AjaxResponse result = examinationService.validateExam(uid,userAnswers);
        return result;
    }

}
