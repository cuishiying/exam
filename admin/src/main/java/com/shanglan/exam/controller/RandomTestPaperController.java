package com.shanglan.exam.controller;

import com.shanglan.exam.entity.RandomTestPaper;
import com.shanglan.exam.service.RandomTestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cuishiying on 2017/6/15.
 * 试卷列表
 * 按题型随机生成试卷、试卷查看、修改、删除
 */
@RestController
@RequestMapping("/randomtestpaper")
public class RandomTestPaperController {

    @Autowired
    private RandomTestPaperService randomTestPaperService;

    @RequestMapping
    public ModelAndView testPaperListView(Pageable pageable){
        ModelAndView model = new ModelAndView("random_test_paper");
        Page<RandomTestPaper> page = randomTestPaperService.findTestPapers(pageable);
        model.addObject("page",page);
        return model;
    }

    @RequestMapping(path = "/add",method = RequestMethod.GET)
    public ModelAndView addRandomTestPaperView(){
        ModelAndView model = new ModelAndView("add_random_testpaper");
        return model;
    }

}
