package com.example.imstagram23back.repository;

import com.example.imstagram23back.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * 2021-07-16 20:40 by 최민서
 */

public interface PostRepository extends JpaRepository<Post, Long> {

    // 생성일자 기준 내림차순 정렬
    List<Post> findAllByOrderByCreatedAtDesc();

    // Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
//    Page findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page findAll(Pageable pageable);
}
