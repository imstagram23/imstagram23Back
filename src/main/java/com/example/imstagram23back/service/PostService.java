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

    // Post 목록 최신순 조회
    @Transactional
    public List<PostResponseDto> getPostList(){

        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(post -> new PostResponseDto(post))
                .collect(Collectors.toList());
    }

    // Post 작성
    @Transactional
    public Long save(MultipartFile image, String writer, String content){
        isNullCheck(image, content);
        String imageUrl = s3Uploader.upload(image);
        Post post = new Post(writer, content, imageUrl);

        return postRepository.save(post).getPostId();
    }

    public void isNullCheck(MultipartFile image, String content){
        if(image == null){
            throw new ApiRequestException("이미지는 반드시 있어야합니다.");
        }
        if(content.isEmpty()){
            throw new ApiRequestException("내용은 반드시 있어야합니다.");
        }
    }

    // Post 삭제
    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 게시물이 없습니다. id = "+id));

        postRepository.deleteById(id);
        s3Uploader.delete(post.getImageUrl());
    }

    // Post 수정
    @Transactional
    public void update(Long id, PostRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ApiRequestException("해당 게시물이 없습니다. id = "+id));
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
