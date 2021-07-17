package com.example.imstagram23back.service;

import com.example.imstagram23back.domain.dto.PostRequestDto;
import com.example.imstagram23back.domain.dto.PostResponseDto;
import com.example.imstagram23back.domain.model.Post;
import com.example.imstagram23back.exception.ApiRequestException;
import com.example.imstagram23back.repository.PostRepository;
import com.example.imstagram23back.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // Post 목록 최신순 조회
    @Transactional
    public List<PostResponseDto> getPostList(){

        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(post -> new PostResponseDto(post))
                .collect(Collectors.toList());
    }

    // Post 작성
    @Transactional
    public Long save(PostRequestDto requestDto, String writer, String imageUrl){
        Post post = new Post(requestDto, writer, imageUrl);
        return postRepository.save(post).getPostId();
    }

    // Post 삭제
    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 게시물이 없습니다. id = "+id));

        postRepository.deleteById(id);
        s3Uploader.delete(post.getImageUrl());
    }
}
