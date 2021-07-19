package com.example.imstagram23back.domain.dto;

import com.example.imstagram23back.domain.model.Comment;
import lombok.Getter;

/*
 * 2021-07-19 14:40 by 최민서
 */

@Getter
public class CommentResponseDto {
    private String content;
    private Long commentId;

    public CommentResponseDto(Comment comment){
        this.content = comment.getContent();
        this.commentId = comment.getCommentId();

    }
}
