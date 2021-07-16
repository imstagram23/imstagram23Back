package com.example.imstagram23back.testdata;


import com.example.imstagram23back.domain.model.User;
import com.example.imstagram23back.domain.model.UserRole;
import com.example.imstagram23back.repository.UserReposiotry;
import com.example.imstagram23back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    UserReposiotry userReposiotry;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        User testUser1 = new User("test@naver.com","12345","테스트", UserRole.USER);
        userReposiotry.save(testUser1);

        

    }
}
