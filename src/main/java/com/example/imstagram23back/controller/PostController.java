package com.example.imstagram23back.controller;

import com.example.imstagram23back.domain.dto.PostRequestDto;
import com.example.imstagram23back.domain.dto.PostResponseDto;
import com.example.imstagram23back.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 2021-07-16 20:40 by 최민서
*/

@RequestMapping("/api/post")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // Post 목록 최신순 조회
    @GetMapping("")
    public List<PostResponseDto> getPostList(){
        return postService.getPostList();
    }

    // Post 생성
    @PostMapping(path="")
    public Map<String, Long> save(MultipartFile image, String content, @AuthenticationPrincipal UserDetails userDetails) {

        Map<String, Long> map= new HashMap<>();
        map.put("postId", postService.save(image, content, userDetails.getUsername()));

        return map;
    }

    // Post 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        postService.delete(id, userDetails.getUsername());
    }

    // Post 수정
    @PutMapping("/{id}")
    public Map<String, Long> update(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails){
        postService.update(id, requestDto, userDetails.getUsername());

        Map<String, Long> map= new HashMap<>();
        map.put("postId", id);
        return map;
    }

    // Post 하나 조회
    @GetMapping("/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }


}
