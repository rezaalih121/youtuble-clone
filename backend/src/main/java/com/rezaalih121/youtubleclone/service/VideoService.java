package com.rezaalih121.youtubleclone.service;

import com.rezaalih121.youtubleclone.dto.UploadVideoResponse;
import com.rezaalih121.youtubleclone.dto.VideoDto;
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

    public UploadVideoResponse uploadVideo(MultipartFile multipartFile){
        // Upload file to AWS S3
        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        Video savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId() , savedVideo.getVideoUrl());

    }

    public VideoDto editVideo(VideoDto videoDto){
        // Find the video by id
        Video savedVido =getVideoById(videoDto.getId());
        // Map the videoDto fields to video
        savedVido.setTitle(videoDto.getTitle());
        savedVido.setDescription(videoDto.getDescription());
        savedVido.setTags(videoDto.getTags());
        savedVido.setThumbnailUrl(videoDto.getThumbnailUrl());
        savedVido.setVideoStatus(videoDto.getVideoStatus());


        // save the video to the database

        videoRepository.save(savedVido);

        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        Video savedVideo = getVideoById(videoId);
        String tuumbnailUrl = s3Service.uploadFile(file);

        savedVideo.setThumbnailUrl(tuumbnailUrl);

        videoRepository.save(savedVideo);

        return tuumbnailUrl;
    }

    Video getVideoById(String videoId){

       return videoRepository.findById(videoId)
                .orElseThrow(()-> new IllegalArgumentException("Cannot find video by id - " + videoId));
    }

    public VideoDto getVideoDetails(String videoId) {

        Video savedVideo = getVideoById(videoId);

         VideoDto videoDto = new VideoDto();
         videoDto.setVideoUrl(savedVideo.getVideoUrl());
         videoDto.setThumbnailUrl(savedVideo.getThumbnailUrl());
         videoDto.setId(savedVideo.getId());
         videoDto.setTags(savedVideo.getTags());
         videoDto.setDescription(savedVideo.getDescription());
         videoDto.setTitle(savedVideo.getTitle());
         videoDto.setVideoStatus(savedVideo.getVideoStatus());

         return videoDto;
    }
}
