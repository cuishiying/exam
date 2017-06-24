package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.entity.TestPaperRule;
import com.shanglan.exam.entity.TestPaperType;
import com.shanglan.exam.repository.QuestionCategoryRepository;
import com.shanglan.exam.repository.TestPaperRuleRepository;
import com.shanglan.exam.repository.TestPaperTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
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

    /**
     * 保存组题规则
     * @param rules
     * @return
     */
    public AjaxResponse submitRules(List<TestPaperRule> rules){

        for (TestPaperRule e:rules) {
            if(e.getEffectiveStartDate().isAfter(e.getEffectiveEndDate())){
                //开始时间大于结束时间
                return AjaxResponse.fail("考试开始时间不得小于结束时间");
            }
            if(null!=testPaperRuleRepository.findByQuestionCategory(e.getQuestionCategory())){
                e.setId(testPaperRuleRepository.findByQuestionCategory(e.getQuestionCategory()).getId());
                e.setQuestionCategory(questionCategoryRepository.findByName(e.getQuestionCategory().getName()));
                e.setTestPaperType(testPaperTypeRepository.findOne(e.getTestPaperType().getId()));
            }
        }

        testPaperRuleRepository.save(rules);
        return AjaxResponse.success();
    }

    /**
     * 获取所有部门组题规则，如果不存在则新建
     * @return
     */
    public List<TestPaperRule> findAll(){
        List<TestPaperRule> rules = testPaperRuleRepository.findAll();
        List<QuestionCategory> categoryList = questionCategoryRepository.findAll();

        if(testPaperTypeRepository.findAll().size()==0){
            TestPaperType tpt = new TestPaperType();
            tpt.setName("每日一考");
            testPaperTypeRepository.save(tpt);
        }

        if(rules.size()<categoryList.size()){
            categoryList.forEach(e->{
                TestPaperRule category = testPaperRuleRepository.findByQuestionCategory(e);
                if(null==category){
                    TestPaperRule item = new TestPaperRule();
                    // TODO: 2017/6/24 table确定后看是否是第一个
                    item.setTestPaperType(testPaperTypeRepository.findAll().get(0));
                    item.setQuestionCategory(e);
                    item.setCountOfMutipleChoice(1);
                    item.setCountOfSingleChoice(1);
                    item.setPassScore(60);
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
