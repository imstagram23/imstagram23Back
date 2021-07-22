package com.example.imstagram23back.domain.model;

import com.example.imstagram23back.domain.dto.CommentRequestDto;
import com.example.imstagram23back.exception.ApiRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;




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

    // 댓글 생성
    public Comment(CommentRequestDto requestDto, Member member, Post post){
        contentBlankCheck(requestDto);

        this.content = requestDto.getContent();
        this.member = member;
        this.post = post;
    }

    // 댓글 수정
    public void update(CommentRequestDto requestDto){
        contentBlankCheck(requestDto);

        this.content = requestDto.getContent();
    }

    // 댓글 내용이 비었는지 확인
    public void contentBlankCheck(CommentRequestDto requestDto){

        if(!StringUtils.hasText(requestDto.getContent())){
            throw new ApiRequestException("내용은 반드시 있어야합니다.");
        }
    }
}
