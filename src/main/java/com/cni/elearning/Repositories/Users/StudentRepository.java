package com.cni.elearning.Repositories.Users;

import com.cni.elearning.Models.Users.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
