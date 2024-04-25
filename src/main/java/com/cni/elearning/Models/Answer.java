package com.cni.elearning.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
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

    public Answer() {
        super();
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(int questionId) {
        this.question = new Question();
        this.question.setId(questionId);
    }

	

}
