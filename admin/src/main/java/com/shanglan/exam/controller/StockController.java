package com.shanglan.exam.controller;

import com.shanglan.exam.entity.ExamRecord;
import com.shanglan.exam.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/27.
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private ExaminationService examinationService;

    /**
     * 考试成绩统计分析
     * @return
     */
    @RequestMapping
    public ModelAndView index(HttpServletRequest request){

        ModelAndView model = new ModelAndView("stock");
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        if(null!=uid){
            List<ExamRecord> page = examinationService.findAll();
            model.addObject("page",page);
        }else{
            model.addObject("page",new PageImpl<ExamRecord>(new ArrayList<>()));
        }

        return model;
    }
}
