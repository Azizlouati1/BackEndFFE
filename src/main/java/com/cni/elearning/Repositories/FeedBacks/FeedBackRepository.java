package com.cni.elearning.Repositories.FeedBacks;

import com.cni.elearning.Models.FeedBacks.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {
    @Query("select f from FeedBack f where f.student.id = :studentId and f.cour.id = :courId")
    FeedBack findByStudentIdAndCourId(@Param("studentId") int studentId, @Param("courId") int courId);
    @Query("select f.Rating from FeedBack f where f.cour.id =:courId")
    List<Float> findRatingByCourId(@Param("courId") int courId);
}
