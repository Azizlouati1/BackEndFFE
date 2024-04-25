package com.cni.elearning.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Chapter;
import com.cni.elearning.Repositories.ChapterRepository;

@Service
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

    @Override
    public Chapter updateChapter(Chapter chapter, int id) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(id);
        if (chapterOptional.isPresent()) {
            return chapterRepository.save(chapter);
        }
        throw new RuntimeException("Chapter not found with id: " + id);
    }

    public List<Chapter> getChaptersByLessonId(int LessonId) {
        return chapterRepository.findByLessonId(LessonId);
    }
    public Chapter updateChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

}
