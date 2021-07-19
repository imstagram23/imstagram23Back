package com.example.imstagram23back.controller;

import com.example.imstagram23back.domain.dto.CommentRequestDto;
import com.example.imstagram23back.domain.dto.CommentResponseDto;
import com.example.imstagram23back.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 2021-07-19 14:40 by 최민서
 */

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // Comment 작성
    @PostMapping("/api/comment/{postId}")
    public CommentResponseDto save(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails){

        return commentService.save(requestDto, postId, userDetails.getUsername());
    }

    // Comment 목록 조회
    @GetMapping("/api/comment/{postId}")
    public List<CommentResponseDto> save(@PathVariable Long postId){
        return commentService.getCommentList(postId);
    }

    // Comment 삭제
    @DeleteMapping("/api/comment/{commentId}")
    public Map<String, Long> delete(@PathVariable Long commentId, @AuthenticationPrincipal UserDetails userDetails){

        commentService.delete(commentId, userDetails.getUsername());

        Map<String, Long> map= new HashMap<>();
        map.put("commentId", commentId);
        return map;
    }

    // Comment 수정
    @PutMapping("/api/comment/{commentId}")
    public CommentResponseDto update(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetails userDetails){

        return commentService.update(commentId, requestDto, userDetails.getUsername());

    }


}
