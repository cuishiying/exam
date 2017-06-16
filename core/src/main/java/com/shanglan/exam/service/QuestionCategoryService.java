package com.shanglan.exam.service;

import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.repository.QuestionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cuishiying on 2017/6/16.
 */
@Service
@Transactional
public class QuestionCategoryService {
    @Autowired
    private QuestionCategoryRepository questionCategoryRepository;

    public QuestionCategory findByName(String name){
        QuestionCategory questionCategory = questionCategoryRepository.findByName(name);
        return questionCategory;
    }

}
