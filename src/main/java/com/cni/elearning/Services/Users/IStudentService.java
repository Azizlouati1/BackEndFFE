package com.cni.elearning.Services.Users;

import com.cni.elearning.Models.Users.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> findAllStudents();

    Optional<Student> findStudentById(int id);

    Student addStudent(Student student);

    Student findStudentById2(int id);

    void deleteInstuctor(int id);


    Student updateStudent(int id, Student student);
}
