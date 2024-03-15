package com.cni.elearning.Models;

import java.util.HashMap;
import java.util.List;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
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
