package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.base.ExcelUtils;
import com.shanglan.exam.entity.Question;
import com.shanglan.exam.entity.QuestionType;
import com.shanglan.exam.repository.QuestionBankRepository;
import com.shanglan.exam.repository.QuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuishiying on 2017/6/13.
 */
@Service
@Transactional
public class QuestionBankService {

    @Autowired
    private QuestionBankRepository questionBankRepository;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    /**
     * 批量导入试题
     * @param in
     * @param file
     * @return
     * @throws Exception
     */
    public AjaxResponse importExcel(InputStream in, MultipartFile file) throws Exception {
        List<List<Object>> listob = ExcelUtils.getBankListByExcel(in,file.getOriginalFilename());
        List<Question> questions=new ArrayList<Question>();

        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Question question = new Question();
            question.setTitle(String.valueOf(lo.get(0)));
            question.setTypeId(Integer.parseInt(String.valueOf(lo.get(1))));
            question.setScore(Integer.parseInt(String.valueOf(lo.get(3))));
            questions.add(question);
        }
        questionBankRepository.save(questions);
        return AjaxResponse.success();
    }

    public AjaxResponse addQuestion(Question question){
        QuestionType type = questionTypeRepository.findOne(question.getTypeId());
        question.setQuestionType(type);
        questionBankRepository.save(question);
        return AjaxResponse.success();
    }

    /**
     * 获取题库
     * @param pageable
     * @return
     */
    public Page<Question> getQuestionBank(Pageable pageable){
        Page<Question> page = questionBankRepository.findAll(pageable);
        return page;
    }
}
