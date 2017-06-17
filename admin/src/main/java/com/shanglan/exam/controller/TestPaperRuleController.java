package com.shanglan.exam.controller;

import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.service.QuestionBankService;
import com.shanglan.exam.service.QuestionCategoryService;
import com.shanglan.exam.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by cuishiying on 2017/6/13.
 * 组卷规则
 */
@RestController
@RequestMapping("/testpaperrule")
public class TestPaperRuleController {

    @Autowired
    private QuestionCategoryService questionCategoryService;

    /**
     * 组卷规则
     * @return
     */
    @RequestMapping
    public ModelAndView addRandomTestPaperView(){
        ModelAndView model = new ModelAndView("testpaper_rule");
        List<QuestionCategory> allCategory = questionCategoryService.findAll();
        model.addObject("allCategory",allCategory);
        return model;
    }
}
