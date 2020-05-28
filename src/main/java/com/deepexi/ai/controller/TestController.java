package com.deepexi.ai.controller;

import com.deepexi.ai.service.UserService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${file.upload.path}")
    String fileUploadPath;

    @Value("${file.download.path}")
    String fileDownloadPath;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public Payload test(){
        return new Payload("Hello World");
    }

    @PostMapping("/upload")
    @ResponseBody
    public Payload upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Payload("上传失败，请选择文件");
        }

        String fileName = file.getOriginalFilename();
        String filePath = fileUploadPath;
        File dest = new File(filePath + fileName);
        String downloadUrl = fileDownloadPath + fileName;
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return new Payload(downloadUrl);
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return new Payload("上传失败！");
    }

}
