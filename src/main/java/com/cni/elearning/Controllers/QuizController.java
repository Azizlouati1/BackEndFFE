package com.cni.elearning.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Services.IQuizService;
import com.cni.elearning.Models.Question;
import com.cni.elearning.Models.Quiz;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final IQuizService quizService;

    public QuizController(IQuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public List<Quiz> getAllQuizzes(){
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return quizzes;
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable int id){
        Quiz quiz = quizService.getQuizById(id);
        return quiz;
    }

    @PostMapping("/")
    public Quiz saveQuiz(@RequestBody Quiz quiz){
        Quiz savedQuiz = quizService.saveQuiz(quiz);
        return savedQuiz;
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable int id){
        quizService.deleteQuiz(id);
    }

    @GetMapping("/questions/{quizId}")
    public List<Question> getQuestionsByQuizId(@PathVariable int quizId){
        List<Question> questions = quizService.getQuestionsByQuizId(quizId);
        return questions;
    }

    @GetMapping("/lesson/{lessonId}")
    public Quiz getQuizByLessonId(@PathVariable int lessonId){
        Quiz quiz = quizService.getQuizByLessonId(lessonId);
        return quiz;
    }

}
