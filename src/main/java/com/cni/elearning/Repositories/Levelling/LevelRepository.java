package com.cni.elearning.Repositories.Levelling;

import com.cni.elearning.Models.Levelling.Level;
import com.cni.elearning.Models.Users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Integer> {
    @Query("SELECT l.student.id , l.student.firstname ,l.student.lastname,l.student.email,l.level,l.levelXP   FROM Level l ORDER BY l.level DESC, l.levelXP DESC LIMIT 5")
    List<Object[]> findTop5StudentsWithLevelsAndXP();
    @Query("select l from Level l where l.student.id =:studentId")
    Level findLevelByStudentId(@Param("studentId") int studentId);
}
