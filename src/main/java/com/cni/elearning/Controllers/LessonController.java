package com.cni.elearning.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Chapter;
import com.cni.elearning.Models.Lesson;
import com.cni.elearning.Models.Quiz;
import com.cni.elearning.Services.ILessonService;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final ILessonService lessonService;

    public LessonController(ILessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public List<Lesson> getAllLessons(){
        List<Lesson> lessons = lessonService.getAllLessons();
        return lessons;
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable int id){
        Lesson lesson = lessonService.getLessonById(id);
        return lesson;
    }

    @PostMapping("/")
    public Lesson saveLesson(@RequestBody Lesson lesson){
        Lesson savedLesson = lessonService.saveLesson(lesson);
        return savedLesson;
    }

    @PutMapping("/{id}")
    public Lesson updateLesson(@RequestBody Lesson lesson, @PathVariable int id){
        Lesson updatedLesson = lessonService.updateLesson(lesson, id);
        return updatedLesson;
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable int id){
        lessonService.deleteLesson(id);
    }

    @GetMapping("/quiz/{lessonId}")
    public Quiz getQuizByLessonId(@PathVariable int lessonId){
        Quiz quiz = lessonService.getQuizByLessonId(lessonId);
        return quiz;
    }

    @GetMapping("/chapters/{lessonId}")
    public List<Chapter> getChaptersByLessonId(@PathVariable int lessonId){
        List<Chapter> chapters = lessonService.getChaptersByLessonId(lessonId);
        return chapters;
    }

    
}
