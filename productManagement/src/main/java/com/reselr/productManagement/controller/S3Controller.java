//package com.reselr.productManagement.controller;
//
//import com.reselr.productManagement.entity.ImageData;
//import com.reselr.productManagement.entity.Item;
//import com.reselr.productManagement.service.S3Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/storage")
//public class S3Controller {
//    private S3Service s3Service;
//
//    @Autowired
//    S3Controller(S3Service s3Service) {
//        this.s3Service = s3Service;
//    }
//
//    @PostMapping("/save")
//    public String saveImageTos3(@RequestBody ImageData imageData){
//        return s3Service.uploadFileToS3(imageData);
//    }
//}
