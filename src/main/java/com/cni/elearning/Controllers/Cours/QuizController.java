package com.cni.elearning.Controllers.Cours;

import java.util.List;
import java.util.Optional;

import com.cni.elearning.Services.Cours.ILessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.cni.elearning.Services.Cours.IQuizService;
import com.cni.elearning.Models.Cours.Quiz;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final IQuizService quizService;
    private final ILessonService lessonService;

    public QuizController(IQuizService quizService, ILessonService lessonService) {
        this.quizService = quizService;
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public ResponseEntity<?>  getAllQuizzes(){
        List<Quiz> quizzes = quizService.getAllQuizzes();
        if (quizzes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No quizzes found");
        }
        else {
            return ResponseEntity.ok(quizzes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable int id) {
        Optional<Quiz> quiz = quizService.getQuizById(id);
        if (quiz.isEmpty()) {
            String errorMessage = "Quiz not found with id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } else {
            return ResponseEntity.ok(quiz); 
        }
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
    public ResponseEntity<?> deleteQuiz(@PathVariable int id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok("Quiz deleted successfully");
    }
    @GetMapping("/{id}/name")
    public String getQuizName(@PathVariable int id) {
        return quizService.getQuizNameById(id);
    }
}
