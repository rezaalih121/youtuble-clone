package com.rezaalih121.youtubleclone.service;

import com.rezaalih121.youtubleclone.dto.CommentDto;
import com.rezaalih121.youtubleclone.dto.UploadVideoResponse;
import com.rezaalih121.youtubleclone.dto.VideoDto;
import com.rezaalih121.youtubleclone.model.Comment;
import com.rezaalih121.youtubleclone.model.Video;
import com.rezaalih121.youtubleclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;
    private final UserService userService;

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
        increaseVideoCount(savedVideo);
        userService.addVideoToHistory(videoId);
        return mapToVideoDto(savedVideo);
    }

    private void increaseVideoCount(Video savedVideo) {
        savedVideo.incrementViewCount();
        videoRepository.save(savedVideo);
    }

    public VideoDto likeVideo(String videoId) {
        // get video by id
        Video videoById = getVideoById(videoId);

        // Increament like count
        // If user already liked the video , then decrement like count
        // like - 0, dislike -0
        // like - 1, dislike -0
        // like - 0, dislike -0

        // like - 0, dislike -1
        // like - 1, dislike -0


        // If user already disliked  the video , then increment like count and decrement dislike count

        if (userService.ifLikedVideo(videoId)) {
            videoById.decrementLikes();
            userService.removeFromLikedVideos(videoId);
        }else if(userService.ifDisLikedVideo(videoId)){
            videoById.decrementDisLikes();
            userService.removeFromDisLikedVideos(videoId);
            videoById.incrementLikes();
            userService.addToLikedVideos(videoId);
        }else {
            videoById.incrementLikes();
            userService.addToLikedVideos(videoId);
        }

        videoRepository.save(videoById);

        return mapToVideoDto(videoById);

    }
    public VideoDto disLikeVideo(String videoId) {
        Video videoById = getVideoById(videoId);

        if (userService.ifDisLikedVideo(videoId)) {
            videoById.decrementDisLikes();
            userService.removeFromDislikedVideos(videoId);
        } else if (userService.ifLikedVideo(videoId)) {
            videoById.decrementLikes();
            userService.removeFromLikedVideos(videoId);
            videoById.incrementDisLikes();
            userService.addToDisLikedVideos(videoId);
        } else {
            videoById.incrementDisLikes();
            userService.addToDisLikedVideos(videoId);
        }

        videoRepository.save(videoById);

        return mapToVideoDto(videoById);
    }

    private VideoDto mapToVideoDto(Video videoById) {
        VideoDto videoDto = new VideoDto();
        videoDto.setVideoUrl(videoById.getVideoUrl());
        videoDto.setThumbnailUrl(videoById.getThumbnailUrl());
        videoDto.setId(videoById.getId());
        videoDto.setPublisherId(videoById.getUserId());
        videoDto.setTitle(videoById.getTitle());
        videoDto.setDescription(videoById.getDescription());
        videoDto.setTags(videoById.getTags());
        videoDto.setVideoStatus(videoById.getVideoStatus());
        videoDto.setLikeCount(videoById.getLikes().get());
        videoDto.setDislikeCount(videoById.getDisLikes().get());
        videoDto.setViewCount(videoById.getViewCount().get());
        return videoDto;
    }

    public void addComment(String videoId, CommentDto commentDto) {
        Video video = getVideoById(videoId);
        Comment comment = new Comment();
        comment.setText(commentDto.getCommentText());
        comment.setAuthorId(commentDto.getAuthorId());
        comment.setCreatedAt(commentDto.getCreatedAt());
        video.addComment(comment);

        videoRepository.save(video);
    }

    public List<CommentDto> getAllComments(String videoId) {
        Video video = getVideoById(videoId);
        List<Comment> commentList = video.getCommentList();
        return commentList.stream().map(this::mapToCommentDto).toList();
    }

    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(comment.getText());
        commentDto.setAuthorId(comment.getAuthorId());
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }

    public List<VideoDto> getAllVideos() {
        return videoRepository.findAll().stream().map(this::mapToVideoDto).toList();
    }

    public void deleteComment (String videoId, CommentDto commentDto) {
        Video video = getVideoById(videoId);
        List<Comment> commentList = video.getCommentList();
        boolean b = commentList.removeIf(comment -> comment.getCreatedAt().equals(commentDto.getCreatedAt() )&& comment.getAuthorId().equals(commentDto.getAuthorId()) );
        video.setCommentList(commentList);
        videoRepository.save(video);

    }

}
