package com.example.imstagram23back.controller;

import com.example.imstagram23back.domain.dto.PostRequestDto;
import com.example.imstagram23back.domain.dto.PostResponseDto;
import com.example.imstagram23back.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("")
    public Map<String, Long> save( @RequestBody PostRequestDto requestDto){
        String writer = "르탄이";
        String imageUrl = "http://sssssss";

        Map<String, Long> map= new HashMap<>();
        map.put("postId", postService.save(requestDto, writer, imageUrl));
        return map;
    }

    // Post 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        postService.delete(id);
    }


}
