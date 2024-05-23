package com.cni.elearning.Models.Cours;

import java.util.List;

import com.cni.elearning.Models.Progress.QuizTests;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
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

    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<QuizTests> quizTests;


    public void setLesson(int lessonId) {
        this.lesson = new Lesson();
        this.lesson.setId(lessonId);
    }

}
