package com.cni.elearning.Repositories.Users;

import com.cni.elearning.Models.Users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select s from students s where s.email = :emailSend")
    Student findByEmail(@Param("emailSend") String emailSend);
}
