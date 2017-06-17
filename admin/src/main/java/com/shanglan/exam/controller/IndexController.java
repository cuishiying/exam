package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.service.QuestionBankService;
import com.shanglan.exam.service.QuestionTypeService;
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
    private QuestionTypeService questionTypeService;

    /**
     * 题库中心
     * @param pageable
     * @return
     */
    @RequestMapping
    public ModelAndView index(@PageableDefault(value = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        ModelAndView model = new ModelAndView("question_bank");
        Page<Question> questionBank = questionBankService.getQuestionBank(pageable);
        Page<QuestionType> types = questionTypeService.findAll(null);
        model.addObject("page",questionBank);
        model.addObject("types",types);
        return model;
    }
}
