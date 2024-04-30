package com.cni.elearning.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    private Lesson lesson;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = true)
    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    private List<Question> questions;
    @Column(nullable = true)
    private int passingScore;
    @Column(nullable = true)
    private int score;

    public Quiz(int id, Lesson lesson, String title, String description, List<Question> questions, int passingScore, int score) {
        super();
        this.id = id;
        this.lesson = lesson;
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.passingScore = passingScore;
        this.score = score;
    }

    public Quiz() {
        super();
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(int lessonId) {
        this.lesson = new Lesson();
        this.lesson.setId(lessonId);
    }

}
