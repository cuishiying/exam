package com.shanglan.exam.service;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.entity.AppVersion;
import com.shanglan.exam.repository.AppVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by cuishiying on 2017/7/4.
 */
@Service
@Transactional
public class AppVersionService {
    @Autowired
    private AppVersionRepository appVersionRepository;

    public AjaxResponse save(AppVersion v){
        v.setUploadTime(LocalDateTime.now());
        appVersionRepository.save(v);
        return AjaxResponse.success();
    }
}
