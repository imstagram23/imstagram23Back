package com.example.imstagram23back.repository;

import com.example.imstagram23back.domain.model.HeartLike;
import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface HeartLikeRepository extends JpaRepository<HeartLike, Long> {

    // 해당 Post에 멤버가 누른 좋아요 조회
    Optional<HeartLike> findByMemberAndPost(Member member, Post post);

    // 해당 Post의 좋아요 총 개수 조회
    Long countByPost(Post post);

    // 해당 Post에 연관된 좋아요 삭제
    Long deleteByPost(Post post);
}
