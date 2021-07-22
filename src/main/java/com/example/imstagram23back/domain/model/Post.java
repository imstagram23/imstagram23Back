package com.example.imstagram23back.domain.model;


import com.example.imstagram23back.domain.dto.PostRequestDto;
import com.example.imstagram23back.exception.ApiRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;



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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    public Post(String content, String imageUrl, Member member){
        this.content = content;
        this.imageUrl = imageUrl;
        this.member = member;
    }

    public void update(PostRequestDto requestDto){
        if(!StringUtils.hasText(requestDto.getContent())){
            throw new ApiRequestException("내용은 반드시 있어야합니다.");
        }
        this.content = requestDto.getContent();
    }

}
