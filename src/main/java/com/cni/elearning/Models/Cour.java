package com.cni.elearning.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cni.elearning.Repositories.InstructorRepository;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Cour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(nullable = false )
    private String category;
    @Column(nullable = false )
    private int difficulty;
    @Column(nullable = false )
    private Boolean premium;
    @Column(nullable = true )
    private String recommendedLevel;
    @Column(nullable = true )
    @OneToMany(mappedBy = "cour",cascade = CascadeType.ALL)
    private List<Lesson> lessons;
    @Column(nullable = true )
    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "instructorId")
    private Instructor instructor = new Instructor();


    public Cour(int id, String title, String description, String category, int difficulty, Boolean premium,
                String recommendedLevel, List<Lesson> lessons, byte[] image, Instructor instructor) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.premium = premium;
        this.recommendedLevel = recommendedLevel;
        this.lessons = lessons;
        this.image = image;
        this.instructor = instructor;
    }
    public Cour() {
        super();
    }



    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public List<Lesson> getLessons() {
        return lessons;
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor( int id ){
        this.instructor.setId(id);
    }
}
