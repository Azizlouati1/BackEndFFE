package com.cni.elearning.Models;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 1000)
    private String answer;
    @Column(nullable = false)
    private Boolean isCorrect;
    @ManyToOne
    private Question question;

    public Answer(int id, String answer, Boolean isCorrect, Question question) {
        super();
        this.id = id;
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

	

}
