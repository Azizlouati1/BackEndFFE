package com.cni.elearning.Models.Events;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column( nullable = false)
    private String title;
    @Column( nullable = false)
    private String description;
    @Column( nullable = false)
    private String subject;
    @Column( nullable = false)
    private Date startDate;
    @Column( nullable = false)
    private Date endDate;
    @Column( nullable = false)
    private String question;
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Participant> participants;

}
