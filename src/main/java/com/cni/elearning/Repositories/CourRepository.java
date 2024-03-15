package com.cni.elearning.Repositories;


import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.cni.elearning.Models.Cour;


public interface CourRepository extends JpaRepository<Cour, Integer> {

    @Query("SELECT c FROM Cour c JOIN c.lessons l WHERE l.id = :lessonId")
    public Cour findByLessonId(@Param("lessonId") Integer lessonId);
    // delete course_id from list of recommendedcourses in the entity cour
    @Modifying
    @Query("UPDATE Cour c SET c.recommendedCourses = :courseId WHERE :courseId MEMBER OF c.recommendedCourses")
    void deleteCourseFromRecommendedCourses(Integer courseId);
}
