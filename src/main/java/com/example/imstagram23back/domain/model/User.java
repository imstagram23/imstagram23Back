package com.example.imstagram23back.domain.model;

import com.example.imstagram23back.domain.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User  {

    public User(String email, String password, String nickname, UserRole role ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
//        this.kakaoId = null;
    }

    public User(SignupRequestDto requestDto){
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.nickname = requestDto.getNickname();
        this.role = UserRole.USER;
    }

//    public User(String username, String password, String email, Long kakaoId) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.kakaoId = kakaoId;
//    }

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

//    @Column(nullable = true)
//    private Long kakaoId;


}
