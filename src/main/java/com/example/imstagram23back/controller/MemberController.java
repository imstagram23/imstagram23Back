package com.example.imstagram23back.controller;


import com.example.imstagram23back.domain.dto.LoginRequestDto;
import com.example.imstagram23back.domain.dto.SignupRequestDto;
import com.example.imstagram23back.domain.dto.TokenDto;
import com.example.imstagram23back.domain.dto.TokenRequestDto;
import com.example.imstagram23back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/api/signup")
    public void registerUser(@RequestBody SignupRequestDto signupRequestDto){
        memberService.registMember(signupRequestDto);
    }

    @PostMapping("/api/login")
    public TokenDto login(@RequestBody LoginRequestDto loginRequestDto){
        return memberService.loginMember(loginRequestDto);
    }

    // 이게왜필요하지..
    @PostMapping("/api/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(memberService.reissue(tokenRequestDto));
    }


}
