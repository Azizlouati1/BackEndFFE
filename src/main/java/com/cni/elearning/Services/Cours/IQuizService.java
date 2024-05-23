package com.cni.elearning.Services.Cours;

import java.util.List;
import java.util.Optional;

import com.cni.elearning.Models.Cours.Question;
import com.cni.elearning.Models.Cours.Quiz;
public interface IQuizService {

    List<Quiz> getAllQuizzes();
    Quiz getQuizById(int id);
    Quiz saveQuiz(Quiz quiz);
    void deleteQuiz(int id);
    List<Question> getQuestionsByQuizId(int quizId);
    Quiz getQuizByLessonId(int lessonId);
    Quiz updateQuiz(int id, Quiz quiz);


    String getQuizNameById(int id);
}
