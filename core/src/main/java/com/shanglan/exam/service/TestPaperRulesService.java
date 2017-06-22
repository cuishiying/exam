package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.entity.QuestionCompositionItem;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.entity.TestPaperType;
import com.shanglan.exam.repository.QuestionCategoryRepository;
import com.shanglan.exam.repository.QuestionTypeRepository;
import com.shanglan.exam.repository.TestPaperRuleRepository;
import com.shanglan.exam.repository.TestPaperTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/14.
 * 组题规则
 */
@Service
@Transactional
public class TestPaperRulesService {


    @Autowired
    private TestPaperRuleRepository testPaperRuleRepository;
    @Autowired
    private QuestionCategoryRepository questionCategoryRepository;
    @Autowired
    private TestPaperTypeRepository testPaperTypeRepository;

    public AjaxResponse submitRules(List<QuestionCompositionItem> rules){
        rules.stream().forEach(e->{
            if(e.getEffectiveStartDate().isAfter(e.getEffectiveEndDate())){
                //开始时间大于结束时间
                return;
            }
            if(null!=testPaperRuleRepository.findByQuestionCategory(e.getQuestionCategory())){
                e.setId(testPaperRuleRepository.findByQuestionCategory(e.getQuestionCategory()).getId());
                e.setQuestionCategory(questionCategoryRepository.findByName(e.getQuestionCategory().getName()));
                e.setTestPaperType(testPaperTypeRepository.findOne(e.getTestPaperType().getId()));
            }
        });
        testPaperRuleRepository.save(rules);
        return AjaxResponse.success();
    }

    /**
     * 获取所有部门组题规则，如果不存在则新建
     * @return
     */
    public List<QuestionCompositionItem> findAll(){
        List<QuestionCompositionItem> rules = testPaperRuleRepository.findAll();
        List<QuestionCategory> categoryList = questionCategoryRepository.findAll();

        if(testPaperTypeRepository.findAll().size()==0){
            TestPaperType tpt = new TestPaperType();
            tpt.setName("每日一考");
            testPaperTypeRepository.save(tpt);
        }

        if(rules.size()<categoryList.size()){
            categoryList.forEach(e->{
                QuestionCompositionItem category = testPaperRuleRepository.findByQuestionCategory(e);
                if(null==category){
                    QuestionCompositionItem item = new QuestionCompositionItem();
                    item.setTestPaperType(testPaperTypeRepository.findAll().get(0));
                    item.setQuestionCategory(e);
                    item.setCountOfMutipleChoice(1);
                    item.setCountOfSingleChoice(1);
                    item.setEffectiveStartDate(LocalTime.now());
                    item.setEffectiveEndDate(LocalTime.now());
                    rules.add(item);
                }
            });
        }
        testPaperRuleRepository.save(rules);

        return rules;
    }
}
