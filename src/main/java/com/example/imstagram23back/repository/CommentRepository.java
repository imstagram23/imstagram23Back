package com.example.imstagram23back.repository;

import com.example.imstagram23back.domain.model.Comment;
import com.example.imstagram23back.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 해당 Post에 연관된 댓글 목록 조회
    List<Comment> findAllByPost(Post post);

    //해당 Post에 있는 댓글 개수 조회
    Long countByPost(Post post);

    // 해당 Post에 연관된 댓글 모두 삭제
    void deleteAllByPost(Post post);
}
