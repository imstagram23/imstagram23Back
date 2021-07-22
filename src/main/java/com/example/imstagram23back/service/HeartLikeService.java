package com.example.imstagram23back.service;


import com.example.imstagram23back.domain.dto.HeartLikeRequestDto;
import com.example.imstagram23back.domain.dto.HeartLikeResponseDto;
import com.example.imstagram23back.domain.model.HeartLike;
import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.Post;
import com.example.imstagram23back.exception.ApiRequestException;
import com.example.imstagram23back.repository.HeartLikeRepository;
import com.example.imstagram23back.repository.MemberRepository;
import com.example.imstagram23back.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HeartLikeService {

    private final HeartLikeRepository heartLikeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public HeartLikeResponseDto heart(Long postId, String userEmail){
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("좋아요기능에서 찾는 유저정보가 없습니다.")
        );

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ApiRequestException("좋아요기능에서 찾는 글 번호가 없습니다.")
        );

        HeartLike findLike = heartLikeRepository.findByMemberAndPost(member, post).orElse(null);

        if(findLike == null){
            HeartLikeRequestDto requestDto = new HeartLikeRequestDto(member, post);
            HeartLike heartLike = new HeartLike(requestDto);
            heartLikeRepository.save(heartLike);
        } else{
            heartLikeRepository.deleteById(findLike.getHeartLikeId());
        }

        return new HeartLikeResponseDto(postId, heartLikeRepository.countByPost(post));

    }
}
