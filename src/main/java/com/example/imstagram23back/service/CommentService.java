package com.example.imstagram23back.service;

import com.example.imstagram23back.domain.dto.CommentRequestDto;
import com.example.imstagram23back.domain.dto.CommentResponseDto;
import com.example.imstagram23back.domain.model.Comment;
import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.Post;
import com.example.imstagram23back.exception.ApiRequestException;
import com.example.imstagram23back.repository.CommentRepository;
import com.example.imstagram23back.repository.MemberRepository;
import com.example.imstagram23back.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*
 * 2021-07-19 14:40 by 최민서
 */

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    // Comment 작성
    @Transactional
    public CommentResponseDto save(CommentRequestDto requestDto, Long postId, String userEmail){

        // 유저 확인
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ApiRequestException("댓글을 달 글이 없습니다.")
        );

        // comment 저장
        Comment comment = new Comment(requestDto, member, post);
        return new CommentResponseDto(commentRepository.save(comment), true);
    }

    // Comment 목록 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentList(Long postId, String userEmail){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ApiRequestException("댓글을 확인할 글이 없습니다.")
        );
        // 유저 조회
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );

        return commentRepository.findAllByPost(post).stream()
                .map(comment->new CommentResponseDto(comment, comment.getMember().getNickname().equals(member.getNickname())))
                .collect(Collectors.toList());

    }

    // Comment 삭제
    @Transactional
    public void delete(Long commentId, String userEmail){
        // 유저 조회
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );
        // 댓글 조회
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiRequestException("댓글이 없습니다.")
        );

        // 유저가 쓴 게시글이 맞는지 확인
        if (!comment.getMember().equals(member)) {
            throw new ApiRequestException("자신이 쓴 댓글만 삭제할 수 있습니다.");
        }

        commentRepository.deleteById(commentId);
    }


    // Comment 수정
    @Transactional
    public CommentResponseDto update(Long commentId, CommentRequestDto requestDto, String userEmail){
        // 유저 조회
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );
        // 댓글 조회
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiRequestException("댓글이 없습니다.")
        );
        // 유저가 쓴 게시글이 맞는지 확인
        if (!comment.getMember().equals(member)) {
            throw new ApiRequestException("자신이 쓴 게시글만 수정할 수 있습니다.");
        }

        comment.update(requestDto);
        return new CommentResponseDto(comment, true);
    }


}
