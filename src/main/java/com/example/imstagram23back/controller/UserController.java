package com.example.imstagram23back.controller;


import com.example.imstagram23back.domain.dto.LoginRequestDto;
import com.example.imstagram23back.domain.dto.SignupRequestDto;
import com.example.imstagram23back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    @PostMapping("/api/signup")
    public void registerUser(@RequestBody SignupRequestDto signupRequestDto){
        userService.registUser(signupRequestDto);
    }

    @PostMapping("/api/login")
    public Map<String, String> login(@RequestBody LoginRequestDto loginRequestDto){
        return userService.loginUser(loginRequestDto);
    }
}
