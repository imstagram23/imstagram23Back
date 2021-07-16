package com.example.imstagram23back.service;


import com.example.imstagram23back.domain.dto.LoginRequestDto;
import com.example.imstagram23back.domain.dto.SignupRequestDto;
import com.example.imstagram23back.domain.model.User;
import com.example.imstagram23back.exception.ApiRequestException;
import com.example.imstagram23back.repository.UserReposiotry;
import com.example.imstagram23back.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 07-16 20:51 by 최왕규
 */


@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserReposiotry userReposiotry;



    @Transactional
    public void registUser(SignupRequestDto requestDto){
        String email = requestDto.getEmail();

        if(email.isEmpty()) {
            throw new ApiRequestException("email(ID)를 입력해주세요");
        }

        // email형식인지 확인하는 정규식 넣기
        if(!requestDto.getEmail().matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")){
            throw new ApiRequestException("올바른 이메일 형식이 아닙니다.");
        }

        // 회원 email(ID)중복확인
        Optional<User> found = userReposiotry.findByEmail(email);
        if (found.isPresent()) {
            throw new ApiRequestException("중복된 사용자 email(ID)가 존재합니다.");
        }

        if(requestDto.getPassword().isEmpty() || requestDto.getPasswordConfirm().isEmpty()) {
            throw new ApiRequestException("패스워드를 입력해 주세요.");
        }

        if(requestDto.getPassword().length() < 4 || requestDto.getPassword().length() > 20) {
            throw new ApiRequestException("비밀번호는  4~20자리를 사용해야 합니다.");
        }

        if ( !(requestDto.getPassword().equals(requestDto.getPasswordConfirm()))){
            throw new ApiRequestException("비밀번호가 서로같지않습니다.");
        }


        // 패스워드 인코딩
        String password= passwordEncoder.encode(requestDto.getPassword());
        // 인코딩된거 안넣어주면 오류나서 이렇게했음
        requestDto.setPassword(password);

        User user = new User(requestDto);
        userReposiotry.save(user);

        // 코드정리시 삭제
        System.out.println("유저 생성완료");
    }


    @Transactional // 이거쓰는거맞낭
    public Map<String, String> loginUser(LoginRequestDto requestDto){
        User user = userReposiotry.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new ApiRequestException("가입되지 않은 email(ID)입니다.")
        );
        // 코드정리시삭제
        System.out.println("user: "+ user.getPassword());
        System.out.println("requestDto : "+ requestDto.getPassword());

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new ApiRequestException("잘못된 비밀번호입니다.");
        }

        Map<String, String> map = new HashMap<>();
        // ROLE필요없으면 여기도삭제하고 createToken도변경
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        map.put("jwt", token);
        return map;
    }
}
