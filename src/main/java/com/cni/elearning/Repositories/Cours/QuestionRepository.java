package com.cni.elearning.Repositories.Cours;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Cours.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{
    
    @Query("SELECT q FROM Question q WHERE q.quiz.id = :quizId")
    public List<Question> findByQuizId(int quizId);
    @Query("DELETE FROM Question q WHERE q.quiz.id = :quizId")
    public void deleteByQuizId(int quizId);
}
