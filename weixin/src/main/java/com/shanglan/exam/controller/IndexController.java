package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.dto.QuestionDTO;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.service.ExaminationService;
import com.shanglan.exam.service.QuestionBankService;
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
public class IndexController {

    @Autowired
    private QuestionBankService questionBankService;
    @Autowired
    private ExaminationService examinationService;

    @RequestMapping
    public ModelAndView testPaperListView(Pageable pageable){
        ModelAndView model = new ModelAndView("index");
        QuestionDTO questionDTO = questionBankService.generateQuestionList(null);
        model.addObject("questions",questionDTO);
        return model;
    }

    @ResponseBody
    @RequestMapping(value = "/examination/submit", method = RequestMethod.POST)
    public AjaxResponse cacheExamPage(@RequestBody List<Map<Integer, List<Map<Integer, Boolean>>>> cacheData,
                                      Integer page, boolean isCache, HttpServletRequest request) {
        Integer empId = (Integer) request.getSession().getAttribute("empId");
        if (empId == null)
            return AjaxResponse.fail("当前未登陆");
        AjaxResponse result = examinationService.cacheExam();
        return result;
    }

}
