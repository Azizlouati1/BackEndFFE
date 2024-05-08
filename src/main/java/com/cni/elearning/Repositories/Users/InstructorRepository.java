package com.cni.elearning.Repositories.Users;


import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Models.Users.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    @Query("select i.courses from instructors i where i.id  = :id ")
    List<Cour> findCourById (@Param("id") int id);
}
