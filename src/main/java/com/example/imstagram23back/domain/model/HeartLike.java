package com.example.imstagram23back.domain.model;


import com.example.imstagram23back.domain.dto.HeartLikeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class HeartLike {

    // ID가 자동으로 생성 및 증가합니다.g
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
