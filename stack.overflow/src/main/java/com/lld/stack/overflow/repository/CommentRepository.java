package com.lld.stack.overflow.repository;

import com.lld.stack.overflow.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByQuestionId(Long questionId);
    List<Comment> findByAnswerId(Long answerId);
    List<Comment> findByQuestionIdAndCommentType(Long questionId, String commentType);
    List<Comment> findByAnswerIdAndCommentType(Long answerId, String commentType);
}
