package com.cni.elearning.Models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
@Entity
public class Question {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Quiz quiz;
    private String question;
    @OneToMany(mappedBy = "question")
    private List<Answer> options;

    public Question(int id, Quiz quiz, String question, List<Answer> options) {
        super();
        this.id = id;
        this.quiz = quiz;
        this.question = question;
        this.options = options;
    }

    public Question() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(int quizId) {
        this.quiz = new Quiz();
        this.quiz.setId(quizId);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getOptions() {
        return options;
    }

    public void setOptions(List<Answer> options) {
        this.options = options;
    }
}
