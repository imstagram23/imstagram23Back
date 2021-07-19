package com.example.imstagram23back.repository;

import com.example.imstagram23back.domain.model.Comment;
import com.example.imstagram23back.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * 2021-07-19 14:40 by 최민서
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
