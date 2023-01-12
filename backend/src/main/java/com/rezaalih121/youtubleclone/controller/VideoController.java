package com.rezaalih121.youtubleclone.controller;

import com.rezaalih121.youtubleclone.dto.UploadVideoResponse;
import com.rezaalih121.youtubleclone.dto.VideoDto;
import com.rezaalih121.youtubleclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file){
        return videoService.uploadVideo(file);

    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String  uploadThumbnail(@RequestParam("file") MultipartFile file , @RequestParam("videoId") String videoId){
        return videoService.uploadThumbnail(file , videoId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto){
        return videoService.editVideo(videoDto);
    }

    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDtails(@PathVariable String videoId){
        return videoService.getVideoDetails(videoId);
    }
}
