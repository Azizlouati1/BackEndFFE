package com.cni.elearning.Models.Progress;


import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Models.Cours.Lesson;
import com.cni.elearning.Models.Users.Student;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(name = "progress_lesson",
            joinColumns = @JoinColumn(name = "progress_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private List<Lesson> completedLessons = new ArrayList<>();

    @ManyToOne
    private Student student = new Student();

    @ManyToOne
    private Cour cour = new Cour();

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Student getStudent() {
        return student;
    }
    public void setStudent( int id ){
        this.student.setId(id);
    }
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Cour getCour() {
        return cour;
    }
    public void setCour( int id ){
        this.cour.setId(id);
    }
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public List<Lesson> getCompletedLessons() {
        return completedLessons;
    }
    public void setCompletedLessons( List<Integer> completedLessons ){
        this.completedLessons = new ArrayList<>();
        for ( Integer id : completedLessons ){
            Lesson lesson = new Lesson();
            lesson.setId(id);
            this.completedLessons.add(lesson);
        }
    }
}

