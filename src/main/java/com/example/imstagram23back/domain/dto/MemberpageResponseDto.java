package com.example.imstagram23back.domain.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MemberpageResponseDto {
    private String nickname;
    private List<String> imageUrl;

    public MemberpageResponseDto(String nickname, List<String> imageUrl) {
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
