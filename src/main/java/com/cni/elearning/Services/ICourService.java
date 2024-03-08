package com.cni.elearning.Services;

import java.util.List;

import com.cni.elearning.Models.Cour;

public interface ICourService {

    List<Cour> getAllCours();
    Cour getCourByLessonId(int lessonId);
    Cour getCourById(int id);
    Cour saveCour(Cour cour);
    void deleteCour(int id);

    
}
