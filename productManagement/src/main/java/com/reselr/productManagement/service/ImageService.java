package com.reselr.productManagement.service;

import com.reselr.productManagement.entity.ImageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@Slf4j
public class ImageService {


    public void saveImage(ImageData imageData){
        String basePath = "C:\\Users\\Ritwik Jha\\Desktop\\Study Material\\microservices\\Project Backend\\imageContainer";

        String base64 = imageData.getImageUrl();
        String savedImageName = imageData.getItemId() + "_" + imageData.getImageName();

        byte[] data = Base64.getDecoder().decode(base64);
        try{
            Files.write(Paths.get(basePath + "\\" + savedImageName + ".png"), data);
            log.info("Success");
        }
        catch (Exception e){
            log.info(e.toString());
        }
    }
}
