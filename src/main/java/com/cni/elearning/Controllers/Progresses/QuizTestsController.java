package com.cni.elearning.Controllers.Progresses;

import com.cni.elearning.Models.Progress.QuizTests;
import com.cni.elearning.Services.Progresses.IQuizTestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tests")
public class QuizTestsController {
    private final IQuizTestsService quizTestsService;

    @GetMapping("/")
    public List<QuizTests> getAllTests(){
        return quizTestsService.getAllQuizTests();
    }
    @GetMapping("/{id}")
    public QuizTests getTestById(@PathVariable int id){
        return quizTestsService.getQuizTestsById(id);
    }
    @PostMapping("/")
    public QuizTests saveQuizTests(@RequestBody QuizTests quizTests){
        return quizTestsService.saveQuizTests(quizTests);
    }
    @DeleteMapping("/{id}")
    public void DeleteTests(@PathVariable int id){
        quizTestsService.deleteQuizTests(id);
    }

}
