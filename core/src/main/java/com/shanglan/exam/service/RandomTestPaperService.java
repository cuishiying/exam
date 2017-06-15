package com.shanglan.exam.service;

import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.RandomTestPaper;
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

    public Page<RandomTestPaper> findTestPapers(Pageable pageable){
        Page<RandomTestPaper> page = randomTestPaperRepository.findAll(pageable);
        return page;
    }

}
