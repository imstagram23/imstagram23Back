package com.example.imstagram23back.service;

import com.example.imstagram23back.domain.dto.PostRequestDto;
import com.example.imstagram23back.domain.dto.PostResponseDto;
import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.Post;
import com.example.imstagram23back.exception.ApiRequestException;
import com.example.imstagram23back.repository.MemberRepository;
import com.example.imstagram23back.repository.PostRepository;
import com.example.imstagram23back.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/*
 * 2021-07-16 20:40 by 최민서
 */

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;
    private final MemberRepository memberRepository;

    // Post 목록 최신순 조회
    @Transactional
    public List<PostResponseDto> getPostList(){

        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(post -> new PostResponseDto(post))
                .collect(Collectors.toList());
    }

    // Post 작성
    @Transactional
    public Long save(MultipartFile image, String content, String userEmail){
        isNullCheck(image, content);

        // 유저 확인
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );

        // 이미지 업로드
        String imageUrl = s3Uploader.upload(image);

        // post 저장
        Post post = new Post(content, imageUrl, member);
        return postRepository.save(post).getPostId();
    }

    // 게시글 내용과 이미지가 있는지 확인
    public void isNullCheck(MultipartFile image, String content){
        // 게시글 내용과 이미지는 필수

        if(image == null){ //.isEmpty()도 되는지 확인해보기
            throw new ApiRequestException("이미지는 반드시 있어야합니다.");
        }
        if(content.isEmpty()){
            throw new ApiRequestException("내용은 반드시 있어야합니다.");
        }
    }

    // Post 삭제
    @Transactional
    public void delete(Long id, String userEmail){
        // 게시글 조회
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 게시물이 없습니다. id = "+id));

        // 유저 조회
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );

        // 유저가 쓴 게시글이 맞는지 확인
        if (!post.getMember().equals(member)) {
            throw new ApiRequestException("자신이 쓴 게시글만 수정할 수 있습니다.");
        }

        // 게시글 삭제
        postRepository.deleteById(id);
        // S3에 업로드했던 이미지도 삭제
        s3Uploader.delete(post.getImageUrl());
    }

    // Post 수정
    @Transactional
    public void update(Long id, PostRequestDto requestDto, String userEmail){
        // 게시글 조회
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 게시물이 없습니다. id = "+id));

        // 유저 조회
        Member member = memberRepository.findByEmail(userEmail).orElseThrow(
                () -> new ApiRequestException("유저가 아닙니다.")
        );

        // 유저가 쓴 게시글이 맞는지 확인
        if (!post.getMember().equals(member)) {
            throw new ApiRequestException("자신이 쓴 게시글만 수정할 수 있습니다.");
        }

        // 게시글 수정
        post.update(requestDto);
    }

    // Post 하나 조회
    @Transactional
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 게시물이 없습니다. id = "+id));
        return new PostResponseDto(post);
    }
}
