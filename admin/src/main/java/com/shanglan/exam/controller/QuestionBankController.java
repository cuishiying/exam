package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.entity.User;
import com.shanglan.exam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;


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
    @Autowired
    private QuestionCategoryService questionCategoryService;


    /**
     * 题库中心
     * @param pageable
     * @return
     */
    @RequestMapping
    public ModelAndView index(String keyword,@PageableDefault(value = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable,HttpServletRequest request){
        ModelAndView model = new ModelAndView("question_bank");
        Page<Question> questionBank = questionBankService.getQuestionBank(keyword,pageable);
        model.addObject("page",questionBank);
        model.addObject("keyword",keyword);
        return model;
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public ModelAndView addQuestion(){
        ModelAndView model = new ModelAndView("question_add");
        List<QuestionType> questionTypes = questionTypeService.findAll();
        List<QuestionCategory> questionCategories = questionCategoryService.findAllAndNoemal();

        model.addObject("questionTypes",questionTypes);
        model.addObject("questionCategories",questionCategories);

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

    @RequestMapping(path = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView questionEditView(@PathVariable Integer id){
        ModelAndView model = new ModelAndView("question_edit");
        Question question = questionBankService.findById(id);
        List<QuestionType> questionTypes = questionTypeService.findAll();
        List<QuestionCategory> questionCategories = questionCategoryService.findAll();
        model.addObject("questionTypes",questionTypes);
        model.addObject("questionCategories",questionCategories);
        model.addObject("question",question);
        return model;
    }

    @RequestMapping(path = "/edit/{id}",method = RequestMethod.POST)
    public AjaxResponse questionEdit(@PathVariable Integer id,@RequestBody Question question){
        AjaxResponse ajaxResponse = questionBankService.editQuestion(id,question);
        return ajaxResponse;
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

    @RequestMapping(path = "/export",method = RequestMethod.GET)
    public AjaxResponse exportGoods(HttpServletRequest request, HttpServletResponse response) {
        response.reset(); //清除buffer缓存
        questionBankService.exportQuestions(response);
        return AjaxResponse.success();
    }


    @RequestMapping(path = "/tip2Pc",method = RequestMethod.GET)
    public ModelAndView tip2Pc(){
        ModelAndView model = new ModelAndView("tip2Pc");
        return model;
    }
}
