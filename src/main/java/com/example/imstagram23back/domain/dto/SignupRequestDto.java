package com.example.imstagram23back.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SignupRequestDto {
    private String email;
    private String nickname;
    private String password;
    private String passwordConfirm;

}
