package com.cni.elearning.Repositories.Progresses;

import com.cni.elearning.Models.Progress.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgressRepository extends JpaRepository<Progress, Integer> {
    @Query("select p from Progress p where p.student.id = :studentId and p.cour.id = :courId")
    Progress findByStudentIdAndCourId(@Param("studentId") int studentId, @Param("courId") int courId);
}
