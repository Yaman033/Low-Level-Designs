package com.lld.stack.overflow.repository;

import com.lld.stack.overflow.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends JpaRepository<Question,Long> {
}
