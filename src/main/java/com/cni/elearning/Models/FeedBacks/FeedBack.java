package com.cni.elearning.Models.FeedBacks;

import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Models.Users.Student;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int Rating;
    private String Comment;
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
}
