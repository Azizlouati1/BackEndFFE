package com.cni.elearning.Services;

import java.util.List;

import com.cni.elearning.Models.Answer;

public interface IAnswerServive {

    List<Answer> getAllAnswers();
    Answer getAnswerById(int id);
    Answer saveAnswer(Answer answer);
    void deleteAnswer(int id);

    
}
