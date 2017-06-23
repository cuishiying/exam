package com.shanglan.exam.service;

import com.shanglan.exam.entity.TestPaperType;
import com.shanglan.exam.repository.TestPaperTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cuishiying on 2017/6/14.
 * 试卷类型
 */
@Service
@Transactional
public class TestPaperTypeService {


    @Autowired
    private TestPaperTypeRepository testPaperTypeRepository;
    public List<TestPaperType> findAll(){
        List<TestPaperType> types = testPaperTypeRepository.findAll();
        return types;
    }
}
