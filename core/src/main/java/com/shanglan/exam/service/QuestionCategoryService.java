package com.shanglan.exam.service;

import com.shanglan.exam.entity.QuestionCategory;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.repository.QuestionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<QuestionCategory> findAll(Pageable pageable){
        Page<QuestionCategory> page = questionCategoryRepository.findAll(pageable);
        return page;
    }

}
