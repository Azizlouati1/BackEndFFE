package com.cni.elearning.Models.Cours;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "lesson_id",unique = true, nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Lesson lesson;

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String description;
    @Lob
    @Column(nullable = true)
    private byte[] video;
    @Column(nullable = true)
    @Lob
    private String coursesContent;
    @Lob
    @Column(nullable = true)
    private String courFile;


    public void setLesson(int LessonId) {
        this.lesson = new Lesson();
        this.lesson.setId(LessonId);
    }

}
