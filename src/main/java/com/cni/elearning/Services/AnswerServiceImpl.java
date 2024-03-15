package com.cni.elearning.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cni.elearning.Models.Answer;
import com.cni.elearning.Repositories.AnswerRepository;

@Service
public class AnswerServiceImpl {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer getAnswerById(int id) {
        return answerRepository.findById(id).get();
    }

    public void deleteAnswer(int id) {
        answerRepository.deleteById(id);
    }
    
}
