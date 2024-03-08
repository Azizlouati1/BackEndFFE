package com.cni.elearning.Services;

import java.util.List;

import com.cni.elearning.Models.Chapter;

public interface IChapterService {
    
    List<Chapter> getAllChapters();
    List<Chapter> getChaptersByLessonId(int LessonId);
    Chapter getChapterById(int id);
    Chapter saveChapter(Chapter chapter);
    void deleteChapter(int id);
}
