package com.cni.elearning.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

    public Quiz findByLessonId(int courId);
    
}
