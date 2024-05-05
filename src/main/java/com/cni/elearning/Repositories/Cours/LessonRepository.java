package com.cni.elearning.Repositories.Cours;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Cours.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{
    
}
