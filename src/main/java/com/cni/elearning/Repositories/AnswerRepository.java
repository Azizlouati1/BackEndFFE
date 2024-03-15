package com.cni.elearning.Repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Answer;
import java.util.List;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query("SELECT a FROM Answer a WHERE a.question.id = :questionId")
    public List<Answer> findByQuestionId(int questionId);

}
