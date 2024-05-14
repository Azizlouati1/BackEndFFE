package com.cni.elearning.Services.Users;

import com.cni.elearning.Models.Users.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAllStudents();

    Student findStudentById(int id);

    Student addStudent(Student student);

    Student findStudentById2(int id);

    void deleteInstructor(int id);


    Student updateStudent(int id, Student student);
}
