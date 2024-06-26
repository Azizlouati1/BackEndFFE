package com.cni.elearning.Services.Cours;

import java.util.List;

import com.cni.elearning.Models.Cours.Question;

public interface IQuestionService {

    List<Question> getAllQuestions();
    List<Question> getQuestionsByQuizId(int quizId);
    Question getQuestionById(int id);
    Question saveQuestion(Question question);
    void deleteQuestion(int id);
    void deleteQuestionByQuizId(int quizId);
    Question updateQuestion( Question question, int id);
}
