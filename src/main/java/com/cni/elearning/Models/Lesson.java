package com.cni.elearning.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
@ManyToOne
    private Cour cour;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;
    
    @Column(nullable = true )
    @OneToMany(mappedBy = "lesson")
    private List<Chapter> chapters;

    @OneToOne(mappedBy = "lesson")
    @PrimaryKeyJoinColumn(name = "id")
    private Quiz quiz;

    public Lesson(int id, Cour cour, String title, String description, List<Chapter> chapters, Quiz quiz) {
        super();
        this.id = id;
        this.cour = cour;
        this.title = title;
        this.description = description;
        this.chapters = chapters;
        this.quiz = quiz;
    }
    
    public Lesson () {
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
    public Cour getCour() {
        return cour;
    }
    public void setCour(int courId) {
        this.cour = new Cour();
        this.cour.setId(courId);
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

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
