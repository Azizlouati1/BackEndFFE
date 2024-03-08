package com.cni.elearning.Services;

import java.util.List;

import com.cni.elearning.Models.Chapter;
import com.cni.elearning.Repositories.ChapterRepository;

public class ChapterServiceImpl implements IChapterService{

    private final ChapterRepository chapterRepository;

    public ChapterServiceImpl(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    public Chapter getChapterById(int id) {
        return chapterRepository.findById(id).get();
    }

    public Chapter saveChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public void deleteChapter(int id) {
        chapterRepository.deleteById(id);
    }
    public List<Chapter> getChaptersByLessonId(int LessonId) {
        return chapterRepository.findByLessonId(LessonId);
    }

}
