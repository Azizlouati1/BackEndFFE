package com.cni.elearning.Models.Events;

import com.cni.elearning.Models.Users.Student;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

        private Date timeDeposed;
        private String answer;
    @ManyToOne
    private Student student = new Student();
    @ManyToOne
    private Event event = new Event();
    private Boolean winner = false ;

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
    public Event getEvent() {
        return event;
    }
    public void setEvent( int id ){
        this.event.setId(id);
    }

}
