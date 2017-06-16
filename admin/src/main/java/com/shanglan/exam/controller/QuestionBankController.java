package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.service.QuestionBankService;
import com.shanglan.exam.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/question")
public class QuestionBankController {

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
    public ModelAndView index(@PageableDefault Pageable pageable){
        ModelAndView model = new ModelAndView("question_bank");
        Page<Question> questionBank = questionBankService.getQuestionBank(pageable);
        Page<QuestionType> types = questionTypeService.findAll(null);
        model.addObject("page",questionBank);
        model.addObject("types",types);
        return model;
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public ModelAndView addQuestion(){
        ModelAndView model = new ModelAndView("question_add");
        return model;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public AjaxResponse addQuestion(@RequestBody Question question, HttpServletRequest request){
        AjaxResponse ajaxResponse = questionBankService.addQuestion(question);
        return ajaxResponse;
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public AjaxResponse deleteQuestion(@PathVariable Integer id, HttpServletRequest request){
        AjaxResponse ajaxResponse = questionBankService.deleteQuestion(id);
        return ajaxResponse;
    }

    @RequestMapping(path = "/detail/{id}",method = RequestMethod.GET)
    public AjaxResponse questionDetail(@PathVariable Integer id){
        Question question = questionBankService.findById(id);
        return AjaxResponse.success(question);
    }

    @RequestMapping(path = "/importExcel", method = RequestMethod.POST)
    public AjaxResponse<?> importExcel(HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        InputStream in = file.getInputStream();
        AjaxResponse ajaxResponse = questionBankService.importExcel(in, file);
        return ajaxResponse;
    }
}
