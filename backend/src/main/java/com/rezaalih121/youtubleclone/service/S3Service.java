package com.rezaalih121.youtubleclone.service;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService{
    public static final String BUCKET_NAME = "rai-youtube-clone";
    private final AmazonS3Client awsS3Client;

    public S3Service() {
        //TODO moving the secrekeys to environment variable

        // to solve the access problem first must make access public to AWS bucket and
        // also in permission part must give Object Ownership ACLs enabled and Buket owner preferred
        // https://stackoverflow.com/questions/70333681/for-an-amazon-s3-bucket-deployment-from-github-how-do-i-fix-the-error-accesscont

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(System.getProperty("cloud.aws.credentials.access-key"), System.getProperty("cloud.aws.credentials.secret-key"));

        awsS3Client = new AmazonS3Client(awsCreds);

    }


    @Override
    public String uploadFile(MultipartFile file){
        // Upload file to AWS S3

        // Prepare a Key
        var fileNameExtention  = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID().toString() + "." +fileNameExtention;
        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            awsS3Client.putObject(BUCKET_NAME, key , file.getInputStream() , metadata);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "An Exception occurred while uploading the file : " + e );
        }
        // make sure to read this file without authentication
        awsS3Client.setObjectAcl(BUCKET_NAME, key , CannedAccessControlList.PublicRead);

        return awsS3Client.getResourceUrl(BUCKET_NAME, key);
    }
}
