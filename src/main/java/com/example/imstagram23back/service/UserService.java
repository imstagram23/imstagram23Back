package com.example.imstagram23back.service;


import com.example.imstagram23back.domain.dto.LoginRequestDto;
import com.example.imstagram23back.domain.dto.SignupRequestDto;
import com.example.imstagram23back.domain.dto.TokenDto;
import com.example.imstagram23back.domain.dto.TokenRequestDto;
import com.example.imstagram23back.domain.model.RefreshToken;
import com.example.imstagram23back.domain.model.User;
import com.example.imstagram23back.exception.ApiRequestException;
import com.example.imstagram23back.repository.RefreshTokenRepository;
import com.example.imstagram23back.repository.UserReposiotry;
import com.example.imstagram23back.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 07-16 20:51 by 최왕규
 */


@RequiredArgsConstructor
@Service
public class UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserReposiotry userReposiotry;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;



    @Transactional
    public void registUser(SignupRequestDto requestDto){
        String email = requestDto.getEmail();

        if (userReposiotry.existsByEmail(email)) {
            throw new ApiRequestException("이미 가입되어 있는 유저입니다");
        }


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
    public TokenDto loginUser(LoginRequestDto requestDto){

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}
