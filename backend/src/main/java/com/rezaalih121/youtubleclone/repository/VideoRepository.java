package com.rezaalih121.youtubleclone.repository;

import com.rezaalih121.youtubleclone.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

// important point to connect to MongoDb using spring boot
// 1. username must not have special characters
// 2. password with special characters must be url encoded https://www.url-encode-decode.com
public interface VideoRepository extends MongoRepository<Video, String> {


    Optional <List<Video>> findAllByUserId(String userId);
}
