package com.example.imstagram23back.testdata;


import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.MemberRole;
import com.example.imstagram23back.domain.model.Post;
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


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member testMember1 = new Member("test@naver.com","12345","테스트", MemberRole.USER);
        memberRepository.save(testMember1);


        Post postTest1 = new Post("작가1","내용1","http://내용내용");
        postRepository.save(postTest1);


        

    }
}
