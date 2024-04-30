package com.cni.elearning.Repositories;


import com.cni.elearning.Models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}
