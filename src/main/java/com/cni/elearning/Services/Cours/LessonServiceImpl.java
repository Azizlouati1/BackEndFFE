package com.cni.elearning.Services.Cours;


import java.util.List;
import java.util.Optional;

import com.cni.elearning.Services.Progresses.IProgressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Cours.Chapter;
import com.cni.elearning.Models.Cours.Lesson;
import com.cni.elearning.Models.Cours.Quiz;
import com.cni.elearning.Repositories.Cours.LessonRepository;
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements ILessonService {
    private final IProgressService progressService;
    private final LessonRepository lessonRepository;
    private final IChapterService chapterService;
    private final IQuizService quizService;
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
        Lesson lessonToSave = lessonRepository.save(lesson);
        progressService.updateLessons();
        return lessonToSave;
    }
    @Override
    @Transactional
    public void deleteLesson(int id) {
        Lesson lessonToDelete = lessonRepository.findById(id).orElse(null);
        assert lessonToDelete != null;
            // Remove associations with Progress entities
        lessonToDelete.getProgresses().forEach(progress -> {
                progress.getCompletedLessons().remove(lessonToDelete);
            });
        lessonRepository.delete(lessonToDelete);
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
    public List<Lesson> getLessonsByCourId(int id){
       return lessonRepository.getLessonsByCourId(id);
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
