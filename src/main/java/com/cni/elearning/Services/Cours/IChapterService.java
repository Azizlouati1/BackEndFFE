package com.cni.elearning.Services.Cours;

import java.util.List;

import com.cni.elearning.Models.Cours.Chapter;

public interface IChapterService {
    
    List<Chapter> getAllChapters();
    List<Chapter> getChaptersByLessonId(int LessonId);
    Chapter getChapterById(int id);
    Chapter saveChapter(Chapter chapter);
    void deleteChapter(int id);
    Chapter updateChapter(Chapter chapter, int id);

    String getChapterNameById(int id);
}
