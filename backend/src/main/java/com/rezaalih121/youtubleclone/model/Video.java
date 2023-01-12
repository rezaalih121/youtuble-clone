package com.rezaalih121.youtubleclone.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document(value = "Video")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private Integer linkes;
    private Integer disLikes;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String thumbnailUrl;
    private List<Comment> commentList;


}
