package com.example.imstagram23back.repository;

import com.example.imstagram23back.domain.model.HeartLike;
import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 2021-07-18 15:05 by 최왕규
 */

public interface HeartLikeRepository extends JpaRepository<HeartLike, Long> {
    Optional<HeartLike> findByMemberAndPost(Member member, Post post);
}
