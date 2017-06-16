package com.shanglan.exam.service;

import com.shanglan.exam.entity.TestPaper;
import com.shanglan.exam.repository.RandomTestPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cuishiying on 2017/6/15.
 */
@Service
@Transactional
public class RandomTestPaperService {

    @Autowired
    private RandomTestPaperRepository randomTestPaperRepository;

    public Page<TestPaper> findTestPapers(Pageable pageable){
        Page<TestPaper> page = randomTestPaperRepository.findAll(pageable);
        return page;
    }

}
