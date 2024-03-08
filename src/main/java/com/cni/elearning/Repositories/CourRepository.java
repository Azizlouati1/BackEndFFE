package com.cni.elearning.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cni.elearning.Models.Cour;

public interface CourRepository extends JpaRepository<Cour, Integer> {

    @Query("SELECT c FROM Cour c JOIN c.lessons l WHERE l.id = :lessonId")
    public Cour findByLessonId(@Param("lessonId") Integer lessonId);

}