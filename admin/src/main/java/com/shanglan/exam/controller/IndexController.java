package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.entity.User;
import com.shanglan.exam.service.QuestionBankService;
import com.shanglan.exam.service.QuestionTypeService;
import com.shanglan.exam.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;


/**
 * Created by cuishiying on 2017/6/13.
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private QuestionBankService questionBankService;
    @Autowired
    private UserService userService;

    /**
     * 题库中心
     * @param pageable
     * @return
     */
    @RequestMapping
    public ModelAndView index(String username,String truename,@PageableDefault(value = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable,HttpServletRequest request){

        ModelAndView model = null;
        if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(truename)){
            User user = userService.findUserByUsernameAndtruename(username, truename);
            request.getSession().invalidate();
            request.getSession().setAttribute("uid", user.getUid());
            model = new ModelAndView("question_bank");
            Page<Question> questionBank = questionBankService.getQuestionBank(pageable);
            model.addObject("page",questionBank);
        }else{
            model = new ModelAndView("redirect:/login");
        }
        return model;
    }
}
