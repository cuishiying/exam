package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.dto.QuestionDTO;
import com.shanglan.exam.dto.UserAnswers;
import com.shanglan.exam.entity.ExamRecord;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.User;
import com.shanglan.exam.service.ExaminationService;
import com.shanglan.exam.service.QuestionBankService;
import com.shanglan.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cuishiying on 2017/6/15.
 */
@RestController
@RequestMapping("/")
public class ExamController {

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

        ModelAndView model = null;
        User user = userService.findUserByUsernameAndtruename(username, truename);
        if(null!=user){
            request.getSession().invalidate();
            request.getSession().setAttribute("uid", user.getUid());
            AjaxResponse ajaxResponse = examinationService.isAttending(user.getUid());
            if(ajaxResponse.isSuccess()){
                model = new ModelAndView("index");
                model.addObject("questions",ajaxResponse.getData());
            }else{
                model = new ModelAndView("exam_status");
                model.addObject("message",ajaxResponse.getMessage());
            }
        }else{
            model = new ModelAndView("exam_status");
            model.addObject("message",AjaxResponse.fail("用户不存在").getMessage());
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

    /**
     * 个人考试成绩
     * @param pageable
     * @return
     */
    @RequestMapping(path = "/record",method = RequestMethod.GET)
    public ModelAndView index(@PageableDefault(value = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, HttpServletRequest request){

        ModelAndView model = new ModelAndView("exam_record");
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        if(null!=uid){
            Page<ExamRecord> page = examinationService.findAll(uid,pageable);
            model.addObject("page",page);
        }else{
            model.addObject("page",new PageImpl<ExamRecord>(new ArrayList<>()));
        }

        return model;
    }

}
