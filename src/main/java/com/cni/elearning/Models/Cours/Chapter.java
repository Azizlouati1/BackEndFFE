package com.cni.elearning.Models.Cours;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Lesson lesson;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;
    @Lob
    @Column(nullable = true)
    private byte[] video;
    @Column(nullable = true)
    @ElementCollection
    private List<String> coursesContent;

    public Chapter(int id, Lesson lesson, String title, String description, byte[] video, List<String> coursesContent) {
        super();
        this.id = id;
        this.lesson = lesson;
        this.title = title;
        this.description = description;
        this.video = video;
        this.coursesContent = coursesContent;
    }
    public Chapter () {
    	super();
    }
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(int LessonId) {
        this.lesson = new Lesson();
        this.lesson.setId(LessonId);
    }

}
