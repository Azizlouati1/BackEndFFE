package com.cni.elearning.Controllers.Cours;

import java.util.List;

import com.cni.elearning.Services.Progresses.IProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Cours.Chapter;
import com.cni.elearning.Models.Cours.Lesson;
import com.cni.elearning.Models.Cours.Quiz;
import com.cni.elearning.Services.Cours.ILessonService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final ILessonService lessonService;
    private final IProgressService progressService;



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
        progressService.updateLessons();
    }

    @GetMapping("/quiz/{lessonId}")
    public Quiz getQuizByLessonId(@PathVariable int lessonId){
        Quiz quiz = lessonService.getQuizByLessonId(lessonId);
        return quiz;
    }

    @GetMapping("/chapters/{lessonId}")
    public List<Chapter> getChaptersByLessonId(@PathVariable int lessonId){
        return  lessonService.getChaptersByLessonId(lessonId);
    }
    @GetMapping("/{id}/name")
    public String getLessonNameById(@PathVariable int id){
        return lessonService.getLessonNameById(id);
    }

    @GetMapping("/cour/{id}")
    public List<Lesson> getLessonByCourId(@PathVariable int id){
        return lessonService.getLessonsByCourId(id);
    }

}
