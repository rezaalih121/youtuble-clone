package com.rezaalih121.youtubleclone.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.amazonaws.auth.profile.internal.ProfileKeyConstants.AWS_ACCESS_KEY_ID;
import static com.amazonaws.auth.profile.internal.ProfileKeyConstants.AWS_SECRET_ACCESS_KEY;

@Configuration
@Service
public class AmazonConfig {

    @Autowired
    private Environment environment;

    @Bean
    public AmazonS3Client S3() {

        AWSCredentials awsCredentials = new BasicAWSCredentials(
                Objects.requireNonNull(environment.getProperty("cloud.aws.credentials.access-key")),
                Objects.requireNonNull(environment.getProperty("cloud.aws.credentials.secret-key"))
        );

        return (AmazonS3Client)AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(Regions.EU_WEST_3).build();
    }
}
