package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.User;
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
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loginView(){


        ModelAndView model = new ModelAndView("login");
        return model;
    }

    @RequestMapping(method = RequestMethod.POST)
    public AjaxResponse login(String username, String truename,HttpServletRequest request ){
        User user = userService.findUserByUsernameAndtruename(username, truename);
        if(null!=user){
            request.getSession().invalidate();
            request.getSession().setAttribute("uid", user.getUid());
        }
        return AjaxResponse.success("login_ok");
    }
}
