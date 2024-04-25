package com.cni.elearning.Controllers;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.cni.elearning.Services.IQuizService;
import com.cni.elearning.Models.Quiz;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final IQuizService quizService;

    public QuizController(IQuizService quizService) {
        this.quizService = quizService;
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
        Quiz quiz = quizService.getQuizById(id);
        if (quiz == null) {
            String errorMessage = "Quiz not found with id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } else {
            return ResponseEntity.ok(quiz); 
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
        try {
            Quiz newQuiz = quizService.saveQuiz(quiz);
            return ResponseEntity.ok(newQuiz);
        } catch (DataIntegrityViolationException e) {
            // Handle foreign key constraint violation
            System.out.println("Failed to add quiz. Lesson not found for quiz: " + quiz.getId());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lesson not found for the quiz.", e);
        } catch (Exception e) {
            // Handle other unexpected exceptions
            System.out.println("Failed to add quiz due to unexpected error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add quiz", e);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuiz(@PathVariable int id, @RequestBody Quiz quiz) {
        Quiz updatedQuiz = quizService.updateQuiz(id, quiz);
        if (updatedQuiz == null) {
            String errorMessage = "Quiz not found with id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } else {
            return ResponseEntity.ok(updatedQuiz);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable int id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.ok("Quiz deleted successfully");
    }
}
