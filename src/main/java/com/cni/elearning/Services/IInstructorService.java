package com.cni.elearning.Services;

import com.cni.elearning.Models.Instructor;

import java.util.List;
import java.util.Optional;

public interface IInstructorService {
    List<Instructor> findAllInstructors();

    Optional<Instructor> findInstructorById(int id);

    Instructor addInstructor(Instructor instructor);

    Instructor findInstructorById2(int id);

    void deleteInstuctor(int id);

    Instructor updateInstructor(Instructor instructor, int id);
}
