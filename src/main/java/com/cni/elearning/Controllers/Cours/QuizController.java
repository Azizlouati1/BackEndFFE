package com.cni.elearning.Controllers.Cours;

import java.util.List;
import java.util.Optional;

import com.cni.elearning.Services.Cours.ILessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.cni.elearning.Services.Cours.IQuizService;
import com.cni.elearning.Models.Cours.Quiz;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final IQuizService quizService;
    private final ILessonService lessonService;

    @GetMapping("/")
    public List<Quiz>  getAllQuizzes(){
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable int id) {
        Quiz quiz = quizService.getQuizById(id);
        assert quiz != null;
            return ResponseEntity.ok(quiz);
    }
    @PostMapping("/")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }
    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable int id, @RequestBody Quiz quiz) {
        return quizService.updateQuiz(id, quiz);
    }
    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable int id) {
        quizService.deleteQuiz(id);
    }
    @GetMapping("/{id}/name")
    public String getQuizName(@PathVariable int id) {
        return quizService.getQuizNameById(id);
    }
}
