package com.cni.elearning.Services.Progresses;

import com.cni.elearning.Models.Cours.Quiz;
import com.cni.elearning.Models.Progress.Progress;
import com.cni.elearning.Models.Progress.QuizTests;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Repositories.Cours.QuizRepository;
import com.cni.elearning.Repositories.Progresses.ProgressRepository;
import com.cni.elearning.Repositories.Progresses.QuizTestsRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizTestsServiceImpl implements IQuizTestsService {
    private final QuizTestsRepository quizTestsRepository;
    private final StudentRepository studentRepository;
    private final QuizRepository quizRepository;
    private final IProgressService progressService;
    private final ProgressRepository progressRepository;

    @Override
    public List<QuizTests> getAllQuizTests() {
        return quizTestsRepository.findAll();
    }

    @Override
    public QuizTests getQuizTestsById(int id) {
        return quizTestsRepository.findById(id).orElse(null);
    }

    @Override
    public QuizTests saveQuizTests(QuizTests quizTests) {
        Student student = studentRepository.findById(quizTests.getStudent().getId()).orElse(null);
        Quiz quiz = quizRepository.findById(quizTests.getQuiz().getId()).orElse(null);
        assert student != null;
        assert quiz != null;
        Progress progress = progressRepository.findByStudentIdAndCourId(student.getId(), quiz.getLesson().getCour().getId());
        assert progress != null;
        QuizTests quizTestsSaved = quizTestsRepository.findQuizTestsByStudentIdAndQuizId(student.getId(), quiz.getId());
        if (quizTestsSaved == null) {
            if ( quizTests.getScore() < quiz.getPassingScore()  || quizTests.getScore() > quiz.getScore() ) {
                throw new RuntimeException("you need to score higher to pass");

            } else {
                quizTestsRepository.save(quizTests);
                progressService.AddLessonToProgress(progress.getId(), quiz.getLesson().getId());
            }
        } else {
            throw new RuntimeException("quiz test already saved");
        }
        return quizTestsRepository.save(quizTests);
    }

    @Override
    public void deleteQuizTests( int id){
        quizTestsRepository.deleteById(id);
    }

}
