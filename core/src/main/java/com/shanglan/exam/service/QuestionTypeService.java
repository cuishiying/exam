package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.repository.QuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cuishiying on 2017/6/14.
 * 题型
 */
@Service
@Transactional
public class QuestionTypeService {

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    public Page<QuestionType> findAll(Pageable pageable){
        Page<QuestionType> page = questionTypeRepository.findAll(pageable);
        return page;
    }

}
