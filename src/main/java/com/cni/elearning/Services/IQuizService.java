package com.cni.elearning.Services;

import java.util.List;

import com.cni.elearning.Models.Question;
import com.cni.elearning.Models.Quiz;
public interface IQuizService {

    List<Quiz> getAllQuizzes();
    Quiz getQuizById(int id);
    Quiz saveQuiz(Quiz quiz);
    void deleteQuiz(int id);
    List<Question> getQuestionsByQuizId(int quizId);
    Quiz getQuizByLessonId(int lessonId);

    
}
