package com.example.imstagram23back.testdata;


import com.example.imstagram23back.domain.dto.HeartLikeRequestDto;
import com.example.imstagram23back.domain.model.HeartLike;
import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.MemberRole;
import com.example.imstagram23back.domain.model.Post;
import com.example.imstagram23back.repository.HeartLikeRepository;
import com.example.imstagram23back.repository.MemberRepository;
import com.example.imstagram23back.repository.PostRepository;
import com.example.imstagram23back.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PostRepository postRepository;

    @Autowired
    HeartLikeRepository heartLikeRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member testMember1 = new Member("test1@naver.com",passwordEncoder.encode("12345"),"테스트1", MemberRole.USER);
        memberRepository.save(testMember1);

        Member testMember2 = new Member("test2@naver.com",passwordEncoder.encode("12345"),"테스트2", MemberRole.USER);
        memberRepository.save(testMember2);

        Member testMember3 = new Member("test3@naver.com",passwordEncoder.encode("12345"),"테스트3", MemberRole.USER);
        memberRepository.save(testMember3);


        Post postTest1 = new Post("작가1","내용1",testMember1);
        postRepository.save(postTest1);

        Post postTest2 = new Post("작가2","내용2",testMember1);
        postRepository.save(postTest2);

        Post postTest3 = new Post("작가3","내용3",testMember1);
        postRepository.save(postTest3);

        Post postTest4 = new Post("작가4","내용4",testMember1);
        postRepository.save(postTest4);

        Post postTest5 = new Post("작가5","내용5",testMember1);
        postRepository.save(postTest5);

        HeartLikeRequestDto heartLikeRequestDto1 = new HeartLikeRequestDto(testMember1, postTest1);
        HeartLike heartLikeTest1 = new HeartLike(heartLikeRequestDto1);
        heartLikeRepository.save(heartLikeTest1);


        HeartLikeRequestDto heartLikeRequestDto2 = new HeartLikeRequestDto(testMember2, postTest1);
        HeartLike heartLikeTest2 = new HeartLike(heartLikeRequestDto2);
        heartLikeRepository.save(heartLikeTest2);

        HeartLikeRequestDto heartLikeRequestDto3 = new HeartLikeRequestDto(testMember3, postTest1);
        HeartLike heartLikeTest3 = new HeartLike(heartLikeRequestDto3);
        heartLikeRepository.save(heartLikeTest3);


        

    }
}
