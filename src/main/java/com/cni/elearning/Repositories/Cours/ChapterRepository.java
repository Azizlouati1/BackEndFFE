package com.cni.elearning.Repositories.Cours;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Cours.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Integer>{

    @Query("SELECT c FROM Chapter c WHERE c.lesson.id = :LessonId")
    public List<Chapter> findByLessonId(int LessonId);
    
}
