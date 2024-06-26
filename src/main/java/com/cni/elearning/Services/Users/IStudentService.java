package com.cni.elearning.Services.Users;

import com.cni.elearning.Models.Users.Student;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface IStudentService {
    List<Student> findAllStudents();

    Student findStudentById(int id);

    Student addStudent(Student student);

    Student findStudentById2(int id);



    void deleteStudent(int id);

    Student updateStudent(int id, Student student);


    @Scheduled(fixedRate = 3000)
    void checkIfPremium();
}
