package com.example.imstagram23back.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponseDto {

    private List<PostPlusResponseDto> postList;
    private Long prePost;
    private boolean next;

    public PageResponseDto(List<PostPlusResponseDto> postPlusResponseDto, Long prePost, boolean next){
        this.postList = postPlusResponseDto;
        this.prePost = prePost;
        this.next = next;
    }
}
