package com.cni.elearning.Repositories;


import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.cni.elearning.Models.Cour;

import java.util.List;


public interface CourRepository extends JpaRepository<Cour, Integer> {

    @Query("SELECT c FROM Cour c JOIN c.lessons l WHERE l.id = :lessonId")
    public Cour findByLessonId(@Param("lessonId") Integer lessonId);
    @Query("SELECT c FROM Cour c WHERE c.instructor.id = :instructorId")
    public List<Cour> findByInstructorId(@Param("instructorId") Integer instructorId);

}
