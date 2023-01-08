package com.rezaalih121.youtubleclone.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service implements FileService{
    @Override
    public String uploadFile(MultipartFile file){
        // Upload file to AWS S3

    return null;
    }
}
