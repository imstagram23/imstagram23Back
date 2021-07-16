package com.example.imstagram23back.domain.model;


import com.example.imstagram23back.domain.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    @Column(name="user_id")
    private String userId;

    public Post(PostRequestDto requestDto, String writer, String imageUrl){
        this.content = requestDto.getContent();
        this.userId = writer;
        this.imageUrl = imageUrl;
    }

}
