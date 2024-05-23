package com.cni.elearning.Services.Cours;


import com.cni.elearning.Models.Cours.Lesson;
import com.cni.elearning.Models.Cours.Quiz;
import com.cni.elearning.Models.Cours.Question;
import com.cni.elearning.Repositories.Cours.LessonRepository;
import com.cni.elearning.Repositories.Cours.QuizRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements IQuizService {

    private final QuizRepository quizRepository;
    private final IQuestionService questionService;
    private final LessonRepository lessonRepository;

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }
    @Override
    public Quiz getQuizById(int id) {
        return quizRepository.findById(id).orElse(null);
    }
    @Override
    public Quiz saveQuiz(Quiz quiz) {
        if(quiz.getQuestions().size()<quiz.getPassingScore()){
            throw new RuntimeException("passing score should be equal or less then the questions");
        }
        Lesson lessonOfQuiz = lessonRepository.findById(quiz.getLesson().getId()).orElse(null);
        assert lessonOfQuiz != null;
        System.out.println(lessonOfQuiz.getQuizzes().size());
        if(!lessonOfQuiz.getQuizzes().isEmpty()){
            throw new RuntimeException("Lesson Already has a quiz");
        }
        Quiz quizSaved = quizRepository.save(quiz);
        List<Question> questionsToSave = quiz.getQuestions();
        quizSaved.setScore(questionsToSave.size());
        for (Question question : questionsToSave){
            question.setQuiz(quizSaved.getId());
            questionService.saveQuestion(question);
        }
        return quizRepository.save(quizSaved);
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
    @Override
    public String getQuizNameById(int id) {
        return quizRepository.findById(id).get().getTitle();
    }

}
