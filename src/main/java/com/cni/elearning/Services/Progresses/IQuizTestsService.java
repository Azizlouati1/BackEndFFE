package com.cni.elearning.Services.Progresses;

import com.cni.elearning.Models.Progress.QuizTests;

import java.util.List;

public interface IQuizTestsService {
    List<QuizTests> getAllQuizTests();

    QuizTests getQuizTestsById(int id);

    QuizTests saveQuizTests(QuizTests quizTests);

    void deleteQuizTests(int id);
}
