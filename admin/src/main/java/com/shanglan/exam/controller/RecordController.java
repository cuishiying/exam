package com.shanglan.exam.controller;

import com.shanglan.exam.entity.ExamRecord;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.repository.ExaminationRepository;
import com.shanglan.exam.service.ExaminationService;
import com.shanglan.exam.service.QuestionBankService;
import com.shanglan.exam.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by cuishiying on 2017/6/13.
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private ExaminationService examinationService;

    /**
     * 个人考试成绩
     * @param pageable
     * @return
     */
    @RequestMapping
    public ModelAndView index(@PageableDefault(value = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, HttpServletRequest request){

        ModelAndView model = new ModelAndView("exam_record");
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        if(null!=uid){
            Page<ExamRecord> page = examinationService.findAll(uid,pageable);
            model.addObject("page",page);
        }else{
            model.addObject("page",new PageImpl<ExamRecord>(new ArrayList<>()));
        }

        return model;
    }



}
