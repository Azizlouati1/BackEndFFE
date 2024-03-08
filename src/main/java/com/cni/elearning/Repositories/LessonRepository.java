package com.cni.elearning.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cni.elearning.Models.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>{
    
}
