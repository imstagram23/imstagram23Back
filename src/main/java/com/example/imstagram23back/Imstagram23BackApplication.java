package com.example.imstagram23back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class Imstagram23BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(Imstagram23BackApplication.class, args);
    }

}
