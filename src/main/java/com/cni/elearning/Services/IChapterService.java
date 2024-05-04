package com.cni.elearning.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.cni.elearning.Models.Chapter;
import org.springframework.web.multipart.MultipartFile;

public interface IChapterService {
    
    List<Chapter> getAllChapters();
    List<Chapter> getChaptersByLessonId(int LessonId);
    Chapter getChapterById(int id);
    Chapter saveChapter(Chapter chapter);
    void deleteChapter(int id);
    Chapter updateChapter(Chapter chapter, int id);

}
