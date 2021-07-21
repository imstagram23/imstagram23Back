package com.example.imstagram23back.domain.dto;

import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
public class PageResponseDto {

    private List<PostResponseDto2> postResponseDto2;
    private Long offset;
    private boolean checkFirst;
    private boolean checkLast;

    public PageResponseDto(List<PostResponseDto2> postResponseDto2, Long offset,  boolean checkFirst, boolean checkLast){
        this.postResponseDto2 = postResponseDto2;
        this.offset = offset;
        this.checkFirst = checkFirst;
        this.checkLast = checkLast;
    }
}
