package com.cni.elearning.Services.Cours;


import java.util.List;

import com.cni.elearning.Models.Cours.Chapter;
import com.cni.elearning.Models.Cours.Lesson;
import com.cni.elearning.Models.Cours.Quiz;

public interface ILessonService {
    List<Lesson> getAllLessons();
    List<Chapter> getChaptersByLessonId(int id);
    Lesson getLessonById(int id);
    Lesson saveLesson(Lesson lesson);
    void deleteLesson(int id);

    List<Lesson> getLessonsByCourId(int id);

    Quiz getQuizByLessonId(int id);
    Lesson updateLesson(Lesson lesson, int id);

    String getLessonNameById(int id);
}
