package com.cni.elearning.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

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
    @Column(nullable = true)
    @Lob
    private byte[] video;
    @Column(nullable = true)
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public List<String> getCoursesContent() {
        return coursesContent;
    }

    public void setCoursesContent(List<String> coursesContent) {
        this.coursesContent = coursesContent;
    }

}
