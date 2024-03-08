package com.cni.elearning.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
    
    // add findByQuizId method
    public List<Question> findByQuizId(int quizId);
}
