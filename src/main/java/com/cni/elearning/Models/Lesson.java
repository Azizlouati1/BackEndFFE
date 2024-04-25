package com.cni.elearning.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Lesson {

    @Getter
    @Setter
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

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Cour getCour() {
        return cour;
    }
    public void setCour(int courId) {
        this.cour = new Cour();
        this.cour.setId(courId);
    }

}
