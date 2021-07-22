package com.example.imstagram23back.domain.dto;

import com.example.imstagram23back.domain.model.Post;
import lombok.Getter;

import java.time.LocalDateTime;



@Getter
public class PostResponseDto {
    private Long postId;
    private String writer;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.writer = post.getMember().getNickname();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
