package com.shanglan.exam.controller;

import com.shanglan.exam.base.AjaxResponse;
import com.shanglan.exam.base.StorageService;
import com.shanglan.exam.entity.AppVersion;
import com.shanglan.exam.enums.AppType;
import com.shanglan.exam.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by cuishiying on 2017/7/4.
 */
@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private AppVersionService appVersionService;

    @RequestMapping("/list")
    public ModelAndView getVersionListByType(@RequestParam(defaultValue="android") AppType type, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView model = new ModelAndView("version_list");
        return model;
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResponse uploadVersion(AppVersion v, @RequestParam MultipartFile file, HttpServletRequest request) {
        try {
            String url = storageService.uploadFile("app","shanglan-"+v.getVersion(), ".apk", file.getSize(), file.getInputStream(), false,request.getSession());
            v.setDownloadUrl(url);
        } catch(IOException e) {
            return AjaxResponse.fail("文件上传失败");
        }
        appVersionService.save(v);
        return AjaxResponse.success();
    }

    @RequestMapping(value="/version/{id}/delete", method=RequestMethod.POST)
    @ResponseBody
    public AjaxResponse deleteVersion(@PathVariable Integer id) {
        return null;
    }
}
