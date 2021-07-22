package com.example.imstagram23back.domain.model;


import com.example.imstagram23back.domain.dto.HeartLikeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class HeartLike {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long heartLikeId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    public HeartLike(HeartLikeRequestDto requestDto){
        this.member = requestDto.getMember();
        this.post = requestDto.getPost();
    }
}
