package com.cni.elearning.Services;


import com.cni.elearning.Models.Quiz;
import com.cni.elearning.Models.Question;
import com.cni.elearning.Repositories.QuizRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service    
public class QuizServiceImpl implements IQuizService{

    private final QuizRepository quizRepository;
    private final IQuestionService questionService;

    public QuizServiceImpl(QuizRepository quizRepository, IQuestionService questionService) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
    }
    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
    @Override
    public Optional<Quiz> getQuizById(int id) {
        return quizRepository.findById(id);
    }
    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
    @Override
    public void deleteQuiz(int id) {
        quizRepository.deleteById(id);
    }
    @Override
    public List<Question> getQuestionsByQuizId(int quizId) {
        return questionService.getQuestionsByQuizId(quizId);
    }
    @Override
    public Quiz getQuizByLessonId(int lessonId) {
        return quizRepository.findByLessonId(lessonId);

    }
    @Override
    public  Quiz updateQuiz(int id, Quiz quiz){
        Optional<Quiz> optionalQuiz = quizRepository.findById(id);
        if(optionalQuiz.isPresent()){
            return quizRepository.save(quiz);
        }
        throw new RuntimeException("Quiz not found with id " + id);
    }

}
