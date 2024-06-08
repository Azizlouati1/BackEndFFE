package com.cni.elearning.Models.Cours;

import java.util.List;

import com.cni.elearning.Models.FeedBacks.FeedBack;
import com.cni.elearning.Models.Progress.Progress;
import com.cni.elearning.Models.Users.Instructor;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
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
    private String recommendedLevel;
    @Column(nullable = true )
    @OneToMany(mappedBy = "cour",cascade = CascadeType.ALL)
    private List<Lesson> lessons;
    @Column(nullable = true )
    @Lob
    private byte[] image;
    private float rating =3;
    @Column(nullable = true )
    private int views = 0;

    @ManyToOne
    @JoinColumn(name = "instructorId")
    private Instructor instructor;

    @OneToMany(mappedBy = "cour",cascade = CascadeType.ALL)
    private List<FeedBack> feedBacks;

    @OneToMany(mappedBy = "cour",cascade = CascadeType.ALL)
    private List<Progress> progresses;

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
        Instructor instructor = new Instructor();
        instructor.setId(id);
        this.instructor = instructor;
    }
}
