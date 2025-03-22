package com.sfa.quizapp2.DTO;

import com.sfa.quizapp2.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
