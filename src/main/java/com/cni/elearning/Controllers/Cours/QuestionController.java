package com.cni.elearning.Controllers.Cours;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Cours.Question;
import com.cni.elearning.Services.Cours.IQuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final IQuestionService questionService;

    public QuestionController(IQuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public List<Question> getAllQuestions(){
        List<Question> questions = questionService.getAllQuestions();
        return questions;
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable int id){
        Question question = questionService.getQuestionById(id);
        return question;
    }

    @PostMapping("/")
    public Question saveQuestion(@RequestBody Question question){
        Question savedQuestion = questionService.saveQuestion(question);
        return savedQuestion;
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable int id){
        questionService.deleteQuestion(id);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsByQuizId(@PathVariable int quizId){
        List<Question> questions = questionService.getQuestionsByQuizId(quizId);
        return questions;
    }
    @DeleteMapping("/deleteQuestionByQuizId/{quizId}")
    public void deleteQuestionByQuizId(@PathVariable int quizId){
        questionService.deleteQuestionByQuizId(quizId);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable int id, @RequestBody Question question){
        return questionService.updateQuestion(question,id);
    }
}
