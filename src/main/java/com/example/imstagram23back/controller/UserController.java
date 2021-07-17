package com.example.imstagram23back.controller;


import com.example.imstagram23back.domain.dto.LoginRequestDto;
import com.example.imstagram23back.domain.dto.SignupRequestDto;
import com.example.imstagram23back.domain.dto.TokenDto;
import com.example.imstagram23back.domain.dto.TokenRequestDto;
import com.example.imstagram23back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping("/api/signup")
    public void registerUser(@RequestBody SignupRequestDto signupRequestDto){
        userService.registUser(signupRequestDto);
    }

    @PostMapping("/api/login")
    public TokenDto login(@RequestBody LoginRequestDto loginRequestDto){
        return userService.loginUser(loginRequestDto);
    }

    // 이게왜필요하지..
    @PostMapping("/api/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(userService.reissue(tokenRequestDto));
    }


}
