package com.cni.elearning.Repositories.Progresses;

import com.cni.elearning.Models.Progress.QuizTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizTestsRepository extends JpaRepository<QuizTests,Integer> {
    @Query("select q from QuizTests q where q.student.id =:studentId and q.quiz.id =:quizId")
    QuizTests findQuizTestsByStudentIdAndQuizId(@Param("studentId") int studentId,@Param("quizId") int quizId);
}
