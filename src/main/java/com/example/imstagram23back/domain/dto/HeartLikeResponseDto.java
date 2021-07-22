package com.example.imstagram23back.domain.dto;

import lombok.Getter;

@Getter
public class HeartLikeResponseDto {
    private Long postId;
    private Long totalLike;

    public  HeartLikeResponseDto(Long postId, Long totalLike){
        this.postId = postId;
        this.totalLike = totalLike;
    }
}

