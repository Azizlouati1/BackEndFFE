package com.cni.elearning.Services;

import java.util.List;

import com.cni.elearning.Models.Answer;

public interface IAnswerService {
    List<Answer> getAllAnswers();
    Answer saveAnswer(Answer answer);
    Answer getAnswerById(int id);
    void deleteAnswer(int id);
    List<Answer> getAnswersByQuestionId(int questionId);
    Answer updateAnswer(Answer answer, int id);
}
