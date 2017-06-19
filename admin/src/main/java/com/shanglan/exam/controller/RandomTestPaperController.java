package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.TestPaper;
import com.shanglan.exam.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/15.
 * 试卷列表
 * 按题型随机生成试卷、试卷查看、修改、删除
 */
@RestController
@RequestMapping("/test")
public class RandomTestPaperController {

    @Autowired
    private QuestionBankService questionBankService;

    @RequestMapping
    public AjaxResponse testPaperListView(Pageable pageable){
        List<Question> questions = questionBankService.generateQuestionList(null);
        return AjaxResponse.success(questions);
    }

}
