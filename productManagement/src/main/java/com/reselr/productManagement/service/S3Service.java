package com.reselr.productManagement.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.reselr.productManagement.configurations.AmazonConfig;
import com.reselr.productManagement.entity.ImageData;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@Slf4j
public class S3Service {

    private AmazonS3 s3client;

    @Autowired
    private AmazonConfig amazonConfig;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @PostConstruct
    private void setS3client(){
        s3client = amazonConfig.s3();
    }


//    @PostConstruct
//    private void initializeAmazon() {
//        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
//        this.s3client = new AmazonS3Client(credentials);
//    }
    public String fileName(ImageData imageData){
        return imageData.getItemId() + "_" + imageData.getImageName();
    }

    public File saveImageLocally(ImageData imageData){
        String basePath = "C:\\Users\\Ritwik Jha\\Desktop\\Study Material\\microservices\\Project Backend\\imageContainer";

        String base64 = imageData.getImageUrl();
        String savedImageName = fileName(imageData);

        byte[] data = Base64.getDecoder().decode(base64);
        Path finalPath;
        try{
            finalPath = Files.write(Paths.get(basePath + "\\" + savedImageName + ".png"), data);
            File rtVal = new File(finalPath.toString());
            log.info("Success");
            return rtVal;
        }
        catch (Exception e){
            log.info(e.toString());
        }

        return null;
    }

    private void uploadFileTos3bucket(String fileName, File file) {

        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
            //    .withCannedAcl(CannedAccessControlList.PublicRead)
        );
    }

    public String uploadFileToS3(ImageData imageData) {

        String fileUrl = "";
        try {
            File file = saveImageLocally(imageData);
            String fileName = fileName(imageData);
            fileUrl = endpointUrl + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

}
