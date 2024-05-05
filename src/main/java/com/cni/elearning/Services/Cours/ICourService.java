package com.cni.elearning.Services.Cours;

import java.util.List;

import com.cni.elearning.Models.Cours.Cour;

public interface ICourService {

    List<Cour> getAllCours();
    Cour getCourByLessonId(int lessonId);
    Cour getCourById(int id);
    Cour saveCour(Cour cour);
    Cour updateCour(Cour cour, int id);
    void deleteCour(int id);
    List<Cour> getCourByInstructorId(int id);

    List<Cour> getCourByInstructorId2(int id);
}
