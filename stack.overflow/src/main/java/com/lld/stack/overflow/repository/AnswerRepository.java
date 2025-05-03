package com.lld.stack.overflow.repository;

import com.lld.stack.overflow.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long>
{
        List<Answer> findByQuestionId(Long questionId);

}
