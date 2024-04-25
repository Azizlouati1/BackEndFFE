package com.cni.elearning.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne()
private Lesson lesson;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = true)
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;
    @Column(nullable = true)
    private int passingScore;
    @Column(nullable = true)
    private int score;

    public Quiz(int id, Lesson lesson, String title, String description, List<Question> questions) {
        super();
        this.id = id;
        this.lesson = lesson;
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.passingScore = 0;
        this.score = 0;
    }

    public Quiz() {
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
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getPassingScore() {
        return passingScore;
    }

    public void setPassingScore(int passingScore) {
        this.passingScore = passingScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
       
}
