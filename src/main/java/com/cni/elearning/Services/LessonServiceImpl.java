package com.cni.elearning.Services;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Chapter;
import com.cni.elearning.Models.Lesson;
import com.cni.elearning.Models.Quiz;
import com.cni.elearning.Repositories.LessonRepository;
@Service
public class LessonServiceImpl implements ILessonService{

    private final LessonRepository lessonRepository;
    private final IChapterService chapterService;
    private final IQuizService quizService;

    public LessonServiceImpl(LessonRepository lessonRepository, IChapterService chapterService, IQuizService quizService) {
        this.lessonRepository = lessonRepository;
        this.chapterService = chapterService;
        this.quizService = quizService;
    }
    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }
    @Override
    public Lesson getLessonById(int id) {
        return lessonRepository.findById(id).get();
    }
    @Override
    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }
    @Override
    public void deleteLesson(int id) {
        lessonRepository.deleteById(id);
    }
    @Override
    public Lesson updateLesson(Lesson lesson , int id){
        Optional<Lesson> optionalLesson = lessonRepository.findById(id);
        if (optionalLesson.isPresent()) {
            return lessonRepository.save(lesson);
        }
        throw new RuntimeException("Lesson not found with id: " + id);
    }
    @Override
    public Quiz getQuizByLessonId(int id) {
        return quizService.getQuizByLessonId(id);
    }
    @Override
    public List<Chapter> getChaptersByLessonId(int id) {
        return chapterService.getChaptersByLessonId(id);
    }
    @Override
    public String getLessonNameById(int id) {
        return lessonRepository.findById(id).get().getTitle();
    }
}
