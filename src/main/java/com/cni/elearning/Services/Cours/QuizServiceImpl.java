package com.cni.elearning.Services.Cours;


import com.cni.elearning.Models.Cours.Lesson;
import com.cni.elearning.Models.Cours.Quiz;
import com.cni.elearning.Models.Cours.Question;
import com.cni.elearning.Repositories.Cours.LessonRepository;
import com.cni.elearning.Repositories.Cours.QuestionRepository;
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
    private final QuestionRepository questionRepository;

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
    public Quiz updateQuiz(int quizId, Quiz updatedQuiz) {
        Quiz existingQuiz = quizRepository.findById(quizId).orElseThrow(() ->
                new RuntimeException("Quiz not found"));
        if (updatedQuiz.getQuestions().size() < updatedQuiz.getPassingScore()) {
            throw new RuntimeException("Passing score should be equal or less than the number of questions");
        }
        Lesson lessonOfQuiz = lessonRepository.findById(existingQuiz.getLesson().getId()).orElse(null);
        assert lessonOfQuiz != null;
        existingQuiz.setTitle(updatedQuiz.getTitle());
        existingQuiz.setDescription(updatedQuiz.getDescription());
        existingQuiz.setPassingScore(updatedQuiz.getPassingScore());
        List<Question> updatedQuestions = updatedQuiz.getQuestions();
        for (Question updatedQuestion : updatedQuestions) {
            if (questionRepository.findById(updatedQuestion.getId()).isPresent()) {
                questionService.updateQuestion(updatedQuestion,updatedQuestion.getId());
            } else {
                updatedQuestion.setQuiz(existingQuiz.getId());
                questionService.saveQuestion(updatedQuestion);
            }
        }
        Quiz savedQuiz = quizRepository.save(existingQuiz);
        savedQuiz.setScore(updatedQuestions.size());
        return quizRepository.save(savedQuiz);
    }
    @Override
    public String getQuizNameById(int id) {
        return quizRepository.findById(id).get().getTitle();
    }

}
