package com.example.imstagram23back.repository;

import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface PostRepository extends JpaRepository<Post, Long> {

    // Post 최신순 정렬
    List<Post> findAllByOrderByCreatedAtDesc();
    
    // Post 페이징
    Page findAll(Pageable pageable);
    
    // 해당 멤버의 Post 조회 : 유저페이지/마이페이지에서 사용
    List<Post> findAllByMember(Member member);
}
