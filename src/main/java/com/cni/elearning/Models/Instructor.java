package com.cni.elearning.Models;

import com.cni.elearning.Repositories.CourRepository;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Setter
@Entity(name = "instructors")
public class Instructor extends User {

    @Column(nullable = true)
    private String speciality;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String biography;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Cour> courses = new ArrayList<>();

    public Instructor(String firstname, String lastname, String email, String password, byte[] image, Role role, String speciality, String address, String phone, String biography) {
        super(firstname, lastname, email, password, role, image);
        this.speciality = speciality;
        this.address = address;
        this.phone = phone;
        this.biography = biography;
    }

    public Instructor() {
        super();
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getBiography() {
        return biography;
    }
}