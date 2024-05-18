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
    private Cour cour;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = true )
    @OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "lesson",cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

    @ManyToMany(mappedBy ="completedLessons")
    private List<Progress> progresses = new ArrayList<>();


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Cour getCour() {
        return cour;
    }
    public void setCour(int courId) {
        this.cour = new Cour();
        this.cour.setId(courId);
    }
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Integer> chapters) {
        this.chapters = new ArrayList<>();
        for ( Integer id : chapters) {
            Chapter chapter = new Chapter();
            chapter.setId(id);
            this.chapters.add(chapter);
        }
    }
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public  List<Quiz> getQuizzes(){
        return quizzes;
    }
    public void setQuizzes(List<Integer> quizzes) {
        this.quizzes = new ArrayList<>();
        for ( Integer id : quizzes) {
            Quiz quiz = new Quiz();
            quiz.setId(id);
            this.quizzes.add(quiz);
        }
    }
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public List<Progress> getProgresses(){
        return progresses;
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
