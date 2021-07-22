package com.example.imstagram23back.repository;

import com.example.imstagram23back.domain.model.HeartLike;
import com.example.imstagram23back.domain.model.Member;
import com.example.imstagram23back.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface HeartLikeRepository extends JpaRepository<HeartLike, Long> {
    Optional<HeartLike> findByMemberAndPost(Member member, Post post);

    // 좋아요 총개수를 위한거
    List<HeartLike> findAllByPost(Post post); // 이것말고 countByPost사용하기로함
    Long countByPost(Post post);

    // 게시글 삭제시 좋아요에있는것도 삭제하기위해서
    Long deleteByPost(Post post);
}
