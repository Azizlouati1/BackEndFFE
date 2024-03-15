package com.cni.elearning.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Question;
import com.cni.elearning.Repositories.QuestionRepository;
@Service    
public class QuestionServiceImpl implements IQuestionService{
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    @Override
    public List<Question> getQuestionsByQuizId(int quizId) {
        return questionRepository.findByQuizId(quizId);
    }
    @Override
    public Question getQuestionById(int id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.get();
    }
    @Override
    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }

   

}
