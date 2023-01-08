package com.rezaalih121.youtubleclone.service;

import com.rezaalih121.youtubleclone.model.Video;
import com.rezaalih121.youtubleclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile multipartFile){
        // Upload file to AWS S3
        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        videoRepository.save(video);

    }
}
