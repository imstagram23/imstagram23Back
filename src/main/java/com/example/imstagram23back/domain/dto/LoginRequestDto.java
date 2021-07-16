package com.example.imstagram23back.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}
