package com.example.imstagram23back.domain.model;


import com.example.imstagram23back.domain.dto.PostRequestDto;
import com.example.imstagram23back.exception.ApiRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * 2021-07-16 20:40 by 최민서
 */

@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long postId;

    @Column
    private String content;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn
    private Member member;

    public Post(String content, String imageUrl, Member member){
        this.content = content;
        this.imageUrl = imageUrl;
        this.member = member;
    }

    public void update(PostRequestDto requestDto){
        if(requestDto.getContent().isEmpty()){
            throw new ApiRequestException("내용은 반드시 있어야합니다.");
        }
        this.content = requestDto.getContent();
    }

}
