package com.cni.elearning.Services.Cours;

import java.util.List;

import org.springframework.stereotype.Service;
import com.cni.elearning.Models.Cours.Answer;
import com.cni.elearning.Repositories.Cours.AnswerRepository;

@Service
public class AnswerServiceImpl implements IAnswerService {

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

    public List<Answer> getAnswersByQuestionId(int questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public Answer updateAnswer(Answer answer, int id) {
        answer.setId(id);
        return answerRepository.save(answer);
    }
    
}
