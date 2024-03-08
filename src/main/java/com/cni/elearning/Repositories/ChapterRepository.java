package com.cni.elearning.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Integer>{

    public List<Chapter> findByLessonId(int LessonId);
    
}
