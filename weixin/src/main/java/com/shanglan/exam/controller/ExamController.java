package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.dto.QuestionDTO;
import com.shanglan.exam.dto.UserAnswers;
import com.shanglan.exam.entity.ExamRecord;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.User;
import com.shanglan.exam.service.ExaminationService;
import com.shanglan.exam.service.QuestionBankService;
import com.shanglan.exam.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cuishiying on 2017/6/15.
 */
@RestController
@RequestMapping("/")
public class ExamController {

    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private UserService userService;

    /**
     * 考卷列表
     * @return
     */
    @RequestMapping
    public ModelAndView examPaper(String username,String truename,HttpServletRequest request){

        ModelAndView model = null;

        Integer uid = 0;
        if(StringUtils.isNotEmpty(username)&&StringUtils.isNotEmpty(truename)){
            User user = userService.findUserByUsernameAndtruename(username, truename);
            request.getSession().invalidate();
            request.getSession().setAttribute("uid", user.getUid());
            uid = user.getUid();
        }else{
            uid = (Integer) request.getSession().getAttribute("uid");
        }


        //防止考生刷卷
        QuestionDTO exam_question = (QuestionDTO) request.getSession().getAttribute("exam_question");
        if(null!=exam_question){

            //计算考试剩余时间
            LocalDateTime exam_start_time = (LocalDateTime) request.getSession().getAttribute("exam_start_time");
            LocalDateTime exam_now_time = LocalDateTime.now();

            long duration = Duration.between(exam_start_time, exam_now_time).toMinutes();

            Integer examDuration = examinationService.examDuration(uid);

            model = new ModelAndView("exam_question");
            model.addObject("questions",exam_question);
            model.addObject("examDuration",examDuration-duration);
            return model;
        }

        if(null==uid){
            model = new ModelAndView("redirect:/login");
        }else{
            //第一次获取试题
            AjaxResponse ajaxResponse = examinationService.isAttending(uid);
            if(ajaxResponse.isSuccess()){

                request.getSession().setAttribute("exam_question",ajaxResponse.getData());
                request.getSession().setAttribute("exam_start_time", LocalDateTime.now());

                model = new ModelAndView("exam_question");
                model.addObject("questions",ajaxResponse.getData());
                model.addObject("examDuration",(Integer)examinationService.examDuration(uid));
            }else{
                model = new ModelAndView("exam_status");
                model.addObject("message",ajaxResponse.getMessage());
            }
        }

        return model;
    }

    /**
     * 提交考卷
     * @param userAnswers
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/examination/submit", method = RequestMethod.POST)
    public AjaxResponse submitExamPage(@RequestBody List<UserAnswers> userAnswers,HttpServletRequest request) {
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        AjaxResponse result = examinationService.validateExam(uid,userAnswers);
        return result;
    }

    /**
     * 个人考试成绩
     * @param pageable
     * @return
     */
    @RequestMapping(path = "/record",method = RequestMethod.GET)
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
