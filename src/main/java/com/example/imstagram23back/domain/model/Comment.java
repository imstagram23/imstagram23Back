package com.example.imstagram23back.domain.model;

import com.example.imstagram23back.domain.dto.CommentRequestDto;
import com.example.imstagram23back.exception.ApiRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * 2021-07-19 14:40 by 최민서
 */


@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    @Id @GeneratedValue
    private Long commentId;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Post post;

    public Comment(CommentRequestDto requestDto, Member member, Post post){
        nullcheck(requestDto);

        this.content = requestDto.getContent();
        this.member = member;
        this.post = post;
    }

    public void update(CommentRequestDto requestDto){
        nullcheck(requestDto);

        this.content = requestDto.getContent();
    }

    public void nullcheck(CommentRequestDto requestDto){

        if(requestDto.getContent().isEmpty()){
            throw new ApiRequestException("내용은 반드시 있어야합니다.");
        }
    }
}
