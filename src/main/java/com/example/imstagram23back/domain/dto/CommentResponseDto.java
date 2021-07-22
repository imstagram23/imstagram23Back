package com.example.imstagram23back.domain.dto;

import com.example.imstagram23back.domain.model.Comment;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String writer;
    private boolean checkMember;

    public CommentResponseDto(Comment comment, boolean checkMember){
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.writer = comment.getMember().getNickname();
        this.checkMember = checkMember;

    }
}
