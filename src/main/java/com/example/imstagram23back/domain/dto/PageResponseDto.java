package com.example.imstagram23back.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponseDto {

    private List<PostPlusResponseDto> postPlusResponseDto;
    private Long offset;
    private boolean checkFirst;
    private boolean checkLast;

    public PageResponseDto(List<PostPlusResponseDto> postPlusResponseDto, Long offset, boolean checkFirst, boolean checkLast){
        this.postPlusResponseDto = postPlusResponseDto;
        this.offset = offset;
        this.checkFirst = checkFirst;
        this.checkLast = checkLast;
    }
}
