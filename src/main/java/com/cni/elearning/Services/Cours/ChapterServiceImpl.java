package com.cni.elearning.Services.Cours;

import java.util.List;
import java.util.Optional;

import com.cni.elearning.Models.Cours.Lesson;
import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Cours.Chapter;
import com.cni.elearning.Repositories.Cours.ChapterRepository;

@Service
public class ChapterServiceImpl implements IChapterService {

    private final ChapterRepository chapterRepository;

    public ChapterServiceImpl(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    @Override
    public List<Chapter> getChaptersByLessonId(int LessonId) {
        return chapterRepository.findByLessonId(LessonId);
    }

    public Chapter getChapterById(int id) {
        return chapterRepository.findById(id).get();
    }

    public Chapter saveChapter(Chapter chapter) {
        Lesson lesson = chapter.getLesson();
        if (lesson != null) {
            return chapterRepository.save(chapter);
        }
        return null;

    }

    public void deleteChapter(int id) {
        chapterRepository.deleteById(id);
    }

    @Override
    public Chapter updateChapter(Chapter chapter, int id) {
        Optional<Chapter> chapterOptional = chapterRepository.findById(id);
        if (id != chapter.getId() ){
            throw new RuntimeException("Write a valid Chapter ID " + id);
        }
        if (chapterOptional.isPresent()) {
            return chapterRepository.save(chapter);
        }
        throw new RuntimeException("Chapter not found with id: " + id);
    }
    @Override
    public String getChapterNameById(int id) {
        return chapterRepository.findById(id).get().getTitle();
    }
    }

