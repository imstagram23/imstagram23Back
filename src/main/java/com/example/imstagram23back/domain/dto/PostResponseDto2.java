package com.example.imstagram23back.domain.dto;


import com.example.imstagram23back.domain.model.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto2 {

    private Long postId;
    private String writer;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean heartLike;
    private Long totalLike; // int -> long
    private Long totalComment;

    public PostResponseDto2(Post post, boolean heartLike, Long totalLike, Long totalComment ) { // int -> long
        this.postId = post.getPostId();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.writer = post.getMember().getNickname();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.heartLike = heartLike;
        this.totalLike = totalLike;
        this.totalComment = totalComment;
    }
}
