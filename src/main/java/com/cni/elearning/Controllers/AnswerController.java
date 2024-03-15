package com.cni.elearning.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.cni.elearning.Models.Answer;
import com.cni.elearning.Services.IAnswerService;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final IAnswerService answerService;

    public AnswerController(IAnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/")
    public List<Answer> getAllAnswers(){
        List<Answer> answers = answerService.getAllAnswers();
        return answers;
    }

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable int id){
        Answer answer = answerService.getAnswerById(id);
        return answer;
    }

    @PostMapping("/")
    public Answer saveAnswer(@RequestBody Answer answer){
        Answer savedAnswer = answerService.saveAnswer(answer);
        return savedAnswer;
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable int id){
        answerService.deleteAnswer(id);
    }
    
    @GetMapping("/questions/{questionId}")
    public List<Answer> getAnswersByQuestionId(@PathVariable int questionId){
        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);
        return answers;
    }

    @PutMapping("/{id}")
    public Answer updateAnswer(@RequestBody Answer answer, @PathVariable int id){
        Answer updatedAnswer = answerService.updateAnswer(answer, id);
        return updatedAnswer;
    }
}
