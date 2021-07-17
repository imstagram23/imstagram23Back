package com.example.imstagram23back.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.imstagram23back.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.UUID;

/*
 * 2021-07-16 20:40 by 최민서
 */

@Component
public class S3Uploader {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    // 생성자로 시도하려 했으나 의존성 주입 시점에는 @Value 어노테이션의 값이 설정되지 않아서 변경
    @PostConstruct
    public void getS3Client() {
        // 자격 증명 객체 얻기
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        // 얻은 자격증명 객체를 이용해서 AmazonS3ClientBuilder로 S3 Client를 가져온다.
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    // S3에 이미지 파일 업로드
    public String upload(MultipartFile image){
        // 이미지파일명 중복 방지
        String fileName = UUID.randomUUID()+"_"+image.getOriginalFilename();

        try {
            // set metadata
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(image.getContentType());

            // PublicRead 권한으로 업로드 됨
            s3Client.putObject(new PutObjectRequest(bucket+"/post/image", fileName, image.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }catch (IOException e){
            throw new ApiRequestException("이미지 업로드에 실패하였습니다.");
        }
        return s3Client.getUrl(bucket, fileName).toString();
    }

    // S3에 이미지 파일 삭제
    public void delete(String imageUrl) {

        try {
            s3Client.deleteObject(bucket+"/post/image", imageUrl.split("amazonaws.com/")[1]);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }


}
