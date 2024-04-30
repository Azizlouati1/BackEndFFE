package com.cni.elearning.Models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Quiz quiz;
    private String question;
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
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

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(int quizId) {
        this.quiz = new Quiz();
        this.quiz.setId(quizId);
    }
}
