package com.rezaalih121.youtubleclone.controller;


import com.rezaalih121.youtubleclone.dto.UploadVideoResponse;
import com.rezaalih121.youtubleclone.dto.VideoDto;
import com.rezaalih121.youtubleclone.dto.CommentDto;
import com.rezaalih121.youtubleclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    @PostMapping
    public ResponseEntity<UploadVideoResponse> uploadVideo(@RequestParam("file") MultipartFile file){
        UploadVideoResponse uploadVideoResponse = videoService.uploadVideo(file);
        if(uploadVideoResponse != null)
            return new ResponseEntity<>(uploadVideoResponse , HttpStatus.CREATED);
        else
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/thumbnail")
    public ResponseEntity<String>  uploadThumbnail(@RequestParam("file") MultipartFile file , @RequestParam("videoId") String videoId){
        String thumbnail = videoService.uploadThumbnail(file, videoId);
        if(thumbnail != null)
            return new ResponseEntity<>(thumbnail , HttpStatus.CREATED);
        else
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public ResponseEntity<VideoDto> editVideoMetadata(@RequestBody VideoDto videoDto){
        VideoDto videoDto1 = videoService.editVideo(videoDto);
        if(videoDto1 != null)
            return new ResponseEntity<>(videoDto1 , HttpStatus.CREATED);
        else
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoDto> getVideoDtails(@PathVariable String videoId){
        VideoDto videoDetails = videoService.getVideoDetails(videoId);
        if(videoDetails != null)
            return new ResponseEntity<>(videoDetails , HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{videoId}/like")
    public ResponseEntity<VideoDto>  likeVideo(@PathVariable String videoId){

        VideoDto videoDto = videoService.likeVideo(videoId);
        if(videoDto != null)
            return new ResponseEntity<>(videoDto , HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{videoId}/disLike")
    public ResponseEntity<VideoDto> disLikeVideo(@PathVariable String videoId){
        VideoDto videoDto = videoService.disLikeVideo(videoId);
        if(videoDto != null)
            return new ResponseEntity<>(videoDto , HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable String videoId , @RequestBody CommentDto commentDto){
        videoService.addComment(videoId , commentDto);

    }

    @PostMapping ("/deleteComment/{videoId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable String videoId , @RequestBody CommentDto cmmentDto){
        CommentDto commentDto = cmmentDto;
        videoService.deleteComment(videoId , commentDto);
    }

    @GetMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable String videoId){
        List<CommentDto> allComments = videoService.getAllComments(videoId);
        if(allComments != null)
            return new ResponseEntity<>(allComments , HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VideoDto>> getAllVideos(){
        List<VideoDto> allVideos = videoService.getAllVideos();
        if(allVideos != null)
            return new ResponseEntity<>(allVideos , HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }


}
