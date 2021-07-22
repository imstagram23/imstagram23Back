package com.example.imstagram23back.controller;


import com.example.imstagram23back.domain.dto.HeartLikeResponseDto;
import com.example.imstagram23back.service.HeartLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HeartLikeController {

    private final HeartLikeService heartLikeService;

    @PostMapping("/api/like/{postId}")
    public HeartLikeResponseDto postLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println("좋아요할시 ID 체크: "+ userDetails.getUsername());

        return heartLikeService.heart(postId, userDetails.getUsername());
    }

}
