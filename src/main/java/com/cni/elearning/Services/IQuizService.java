package com.cni.elearning.Services;

import java.util.List;
import java.util.Optional;

import com.cni.elearning.Models.Question;
import com.cni.elearning.Models.Quiz;
public interface IQuizService {

    List<Quiz> getAllQuizzes();
    Optional<Quiz> getQuizById(int id);
    Quiz saveQuiz(Quiz quiz);
    void deleteQuiz(int id);
    List<Question> getQuestionsByQuizId(int quizId);
    Quiz getQuizByLessonId(int lessonId);
    Quiz updateQuiz(int id, Quiz quiz);

    
}
