package com.cni.elearning.Models.Users;

import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Models.Events.Participant;
import com.cni.elearning.Models.FeedBacks.FeedBack;
import com.cni.elearning.Models.Levelling.Level;
import com.cni.elearning.Models.Paiements.Payments;
import com.cni.elearning.Models.Paiements.Refund;
import com.cni.elearning.Models.Progress.Progress;
import com.cni.elearning.Models.Progress.QuizTests;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name ="students")
public class Student extends User {
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Chat> chats = new ArrayList<>();
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Participant> participants = new ArrayList<>();
    @OneToMany(mappedBy = "student" , cascade = CascadeType.ALL)
    private List<FeedBack> feedBacks = new ArrayList<>();
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Progress> progresses = new ArrayList<>();
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private Level level;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Payments> payments;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "transactionId")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Refund> refund;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<QuizTests> quizTests;
    private Boolean isSubscribed = false;
    private LocalDateTime dateEnd ;
    private String phoneNumber;
    @Column(nullable = true)
    private int age = 0;
    @Column(nullable = true)
    private String gender = "Male";

}
