package com.example.imstagram23back.controller;

import com.example.imstagram23back.domain.dto.*;
import com.example.imstagram23back.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // Post 목록 최신순 조회
    @GetMapping("/api/post")
    public List<PostPlusResponseDto> getPostList2(@AuthenticationPrincipal UserDetails userDetails){
        return postService.getPostList2(userDetails.getUsername());
    }


    // Post 생성
    @PostMapping("/api/post")
    public PostResponseDto save(MultipartFile image, String content, @AuthenticationPrincipal UserDetails userDetails) {

        return postService.save(image, content, userDetails.getUsername());
    }

    // Post 삭제
    @DeleteMapping("/api/post/{id}")
    public Map<String, Long> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        postService.delete(id, userDetails.getUsername());

        Map<String, Long> map= new HashMap<>();
        map.put("postId", id);
        return map;
    }

    // Post 수정
    @PutMapping("/api/post/{id}")
    public PostResponseDto update(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails){

        return postService.update(id, requestDto, userDetails.getUsername());
    }

    // Post 하나 조회
    @GetMapping("/api/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }


    // 페이지 인피니트 스크롤대비
    @GetMapping("/api/post/page")
    public PageResponseDto getPage(@RequestParam("page") int page, @AuthenticationPrincipal UserDetails userDetails){
        return postService.getPage(userDetails.getUsername(), page);
    }

    // 유저페이지 조회
    @GetMapping("/api/memberpage/{nickname}")
    public MemberpageResponseDto getMemberPage(@PathVariable String nickname){
        return postService.getMemberPage(nickname);
    }

    // 마이페이지 조회
    @GetMapping("/api/mypage")
    public MemberpageResponseDto getMyPage(@AuthenticationPrincipal UserDetails userDetails){
        return postService.getMyPage(userDetails.getUsername());
    }

}
