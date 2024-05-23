package com.cni.elearning.Controllers.Cours;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.cni.elearning.Models.Cours.Answer;
import com.cni.elearning.Services.Cours.IAnswerService;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final IAnswerService answerService;

    public AnswerController(IAnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/")
    public List<Answer> getAllAnswers(){
        return answerService.getAllAnswers();
    }

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable int id){
        return answerService.getAnswerById(id);
    }

    @PostMapping("/")
    public Answer saveAnswer(@RequestBody Answer answer){
        return answerService.saveAnswer(answer);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable int id){
        answerService.deleteAnswer(id);
    }
    
    @GetMapping("/questions/{questionId}")
    public List<Answer> getAnswersByQuestionId(@PathVariable int questionId){
        return answerService.getAnswersByQuestionId(questionId);
    }

    @PutMapping("/{id}")
    public Answer updateAnswer(@RequestBody Answer answer, @PathVariable int id){
        return answerService.updateAnswer(answer, id);
    }
}
