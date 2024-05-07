package com.cni.elearning.Models.Users;

import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Models.Events.Participant;
import com.cni.elearning.Models.FeedBacks.FeedBack;
import com.cni.elearning.Models.Levelling.Level;
import com.cni.elearning.Models.Progress.Progress;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.service.annotation.GetExchange;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name ="students")
public class Student extends User {
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Chat> chats = new ArrayList<>();
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Participant> participants = new ArrayList<>();
    @OneToMany(mappedBy = "student" , cascade = CascadeType.ALL)
    private List<FeedBack> feedBacks = new ArrayList<>();
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Progress> progresses = new ArrayList<>();
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private Level level;

}
