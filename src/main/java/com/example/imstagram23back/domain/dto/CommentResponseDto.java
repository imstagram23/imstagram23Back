package com.example.imstagram23back.domain.dto;

import com.example.imstagram23back.domain.model.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

/*
 * 2021-07-19 14:40 by 최민서
 */

@Getter
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();

    }
}
