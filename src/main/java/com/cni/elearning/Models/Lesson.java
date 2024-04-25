package com.cni.elearning.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.validation.constraints.Size;
import jakarta.persistence.*;
import lombok.Data;
@Data
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

    @OneToMany(mappedBy = "lesson")
    private List<Quiz> quizzes;

    public Lesson(int id, Cour cour, String title, String description, List<Chapter> chapters, List<Quiz> quizzes) {
        super();
        this.id = id;
        this.cour = cour;
        this.title = title;
        this.description = description;
        this.chapters = chapters;
        this.quizzes = quizzes;
    }
    
    public Lesson () {
    	super();
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public int getCour() {
        return cour.getId();
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

}
