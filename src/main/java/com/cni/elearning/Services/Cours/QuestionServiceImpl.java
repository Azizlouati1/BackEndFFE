package com.cni.elearning.Services.Cours;

import java.util.List;
import java.util.Optional;

import com.cni.elearning.Models.Cours.Answer;
import com.cni.elearning.Models.Cours.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Cours.Question;
import com.cni.elearning.Repositories.Cours.QuestionRepository;
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements IQuestionService {
    private final QuestionRepository questionRepository;
    private final IAnswerService answerService;
    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
    @Override
    public Question saveQuestion(Question question) {
        Question questionSaved = questionRepository.save(question);
        List<Answer> answersToSave = question.getOptions();
        for (Answer answer : answersToSave){
            answer.setQuestion(questionSaved.getId());
            answerService.saveAnswer(answer);
        }
        return questionRepository.save(questionSaved);
    }
    @Override
    public List<Question> getQuestionsByQuizId(int quizId) {
        return questionRepository.findByQuizId(quizId);
    }
    @Override
    public Question getQuestionById(int id) {
        return questionRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }
    @Override
    public void deleteQuestionByQuizId(int quizId) {
        questionRepository.deleteByQuizId(quizId);
    }
    @Override
    public Question updateQuestion( Question question, int id) {
        Optional<Question> question1 = questionRepository.findById(id);
        if(question1.isPresent()) {
            return questionRepository.save(question);
        }
        throw new RuntimeException( "Question not found with id "+id);
    }
   

}
