package com.cni.elearning.Models;

import com.cni.elearning.Repositories.CourRepository;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
    public void setCourses(List<Cour> courses) {
        this.courses = courses;
    }
}