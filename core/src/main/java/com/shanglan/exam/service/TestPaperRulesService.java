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
            e.setQuestionCategory(questionCategoryRepository.findByName(e.getQuestionCategory().getName()));
            e.setTestPaperType(testPaperTypeRepository.findOne(e.getTestPaperType().getId()));
        });
        testPaperRuleRepository.save(rules);
        return AjaxResponse.success();
    }

    public List<QuestionCompositionItem> findAll(){
        List<QuestionCompositionItem> rules = testPaperRuleRepository.findAll();
        if(rules.size()==0){
            List<QuestionCategory> categoryList = questionCategoryRepository.findAll();
            categoryList.forEach(e->{
                QuestionCompositionItem item = new QuestionCompositionItem();
                item.setTestPaperType(testPaperTypeRepository.findAll().get(0));
                item.setQuestionCategory(e);
                item.setCountOfMutipleChoice(1);
                item.setCountOfSingleChoice(1);
                item.setEffectiveStartDate(LocalDate.now());
                item.setEffectiveEndDate(LocalDate.now());
                rules.add(item);
            });
        }
        return rules;
    }
}
