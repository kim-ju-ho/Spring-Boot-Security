package com.sparta.springdetailpersonaltraining.repository;

import com.sparta.springdetailpersonaltraining.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{
    List<Comment> findAllByOrderByCreatedAtDesc();
    List<Comment> findAllByParentsIdOrderByCreatedAtDesc(Long id);
}
