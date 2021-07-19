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
    private Long totalLike; // int -> long
    private Long totalComment;
    private boolean heartLike;
    private boolean checkMember;

    public PostResponseDto2(Post post, Long totalLike, Long totalComment, boolean heartLike, boolean checkMember ) { // int -> long
        this.postId = post.getPostId();
        this.content = post.getContent();
        this.imageUrl = post.getImageUrl();
        this.writer = post.getMember().getNickname();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.heartLike = heartLike;
        this.totalLike = totalLike;
        this.totalComment = totalComment;
        this.checkMember = checkMember;
    }
}
