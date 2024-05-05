package com.cni.elearning.Repositories.Users;


import com.cni.elearning.Models.Users.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
