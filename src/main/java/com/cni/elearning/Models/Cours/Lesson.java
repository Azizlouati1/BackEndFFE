package com.cni.elearning.Models.Cours;

import java.util.ArrayList;
import java.util.List;

import com.cni.elearning.Models.Progress.Progress;
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
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Cour cour;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = true )
    @OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Chapter> chapters = new ArrayList<>();

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Quiz> quizzes = new ArrayList<>();

    @ManyToMany(mappedBy ="completedLessons")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Progress> progresses = new ArrayList<>();



    public void setCour(int courId) {
        this.cour = new Cour();
        this.cour.setId(courId);
    }

    public void setChapters(List<Integer> chapters) {
        for ( Integer id : chapters) {
            Chapter chapter = new Chapter();
            chapter.setId(id);
            this.chapters.add(chapter);
        }
    }

    public void setProgresses(List<Integer> progresses) {
        this.progresses = new ArrayList<>();
        for ( Integer id : progresses) {
            Progress progress = new Progress();
            progress.setId(id);
            this.progresses.add(progress);
        }
    }

}
