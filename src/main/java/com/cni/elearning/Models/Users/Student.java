package com.cni.elearning.Models.Users;

import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Models.Events.Participant;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity(name ="students")
public class Student extends User {
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Chat> chats = new ArrayList<>();
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Participant> participants = new ArrayList<>();

    public Student(String firstname, String lastname, String email, String password, byte[] image, Role role) {
        super(firstname,lastname,email,password,role,image);
    }

}
