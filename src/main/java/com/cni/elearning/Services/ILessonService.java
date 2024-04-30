package com.cni.elearning.Services;


import java.util.List;

import com.cni.elearning.Models.Chapter;
import com.cni.elearning.Models.Lesson;
import com.cni.elearning.Models.Quiz;

public interface ILessonService {
    List<Lesson> getAllLessons();
    List<Chapter> getChaptersByLessonId(int id);
    Lesson getLessonById(int id);
    Lesson saveLesson(Lesson lesson);
    void deleteLesson(int id);
    Quiz getQuizByLessonId(int id); 
    Lesson updateLesson(Lesson lesson, int id);

    String getLessonNameById(int id);
}
