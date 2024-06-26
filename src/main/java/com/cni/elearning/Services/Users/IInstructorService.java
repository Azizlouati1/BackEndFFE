package com.cni.elearning.Services.Users;

import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Models.Users.Instructor;

import java.util.List;
import java.util.Optional;

public interface IInstructorService {
    List<Instructor> findAllInstructors();

    Optional<Instructor> findInstructorById(int id);

    Instructor addInstructor(Instructor instructor);

    Instructor findInstructorById2(int id);

    void deleteInstuctor(int id);

    Instructor updateInstructor(Instructor instructor, int id);

    List<Cour> getCourByInstructorId(int id);
}
