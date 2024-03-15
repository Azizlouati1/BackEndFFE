package com.cni.elearning.Repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

    @Query("SELECT q FROM Quiz q WHERE q.lesson.id = :lessonId")
    public Quiz findByLessonId(int lessonId);
    
}
