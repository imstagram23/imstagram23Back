package com.example.imstagram23back.domain.dto;


import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HeartLikeRequestDto {
    private Member member;
    private Post post;
}
