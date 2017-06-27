package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.*;
import com.shanglan.exam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    private TestPaperRulesService testPaperRulesService;

    @Autowired
    private TestPaperTypeService testPaperTypeService;


    /**
     * 组卷规则
     * @return
     */
    @RequestMapping
    public ModelAndView testPaperRuleView(){
        ModelAndView model = new ModelAndView("testpaper_rule");
        List<TestPaperRule> rules = testPaperRulesService.findAll();
        List<TestPaperType> paperTypes = testPaperTypeService.findAll();
        model.addObject("rules",rules);
        model.addObject("paperTypes",paperTypes);
        return model;
    }

    @RequestMapping(path = "/save",method = RequestMethod.POST)
    public AjaxResponse submitRules(@RequestBody List<TestPaperRule> rules){
        AjaxResponse ajaxResponse = testPaperRulesService.submitRules(rules);
        return ajaxResponse;
    }
}
