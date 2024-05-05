package com.cni.elearning.Services.Cours;

import java.util.List;

import com.cni.elearning.Models.Cours.Answer;

public interface IAnswerService {
    List<Answer> getAllAnswers();
    Answer saveAnswer(Answer answer);
    Answer getAnswerById(int id);
    void deleteAnswer(int id);
    List<Answer> getAnswersByQuestionId(int questionId);
    Answer updateAnswer(Answer answer, int id);
}
