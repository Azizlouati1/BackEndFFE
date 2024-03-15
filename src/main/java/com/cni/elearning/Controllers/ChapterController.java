package com.cni.elearning.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Chapter;
import com.cni.elearning.Services.IChapterService;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    private final IChapterService chapterService;

    public ChapterController(IChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/")
    public List<Chapter> getAllChapters(){
        List<Chapter> chapters = chapterService.getAllChapters();
        return chapters;
    }

    @GetMapping("/{id}")
    public Chapter getChapterById(@PathVariable int id){
        Chapter chapter = chapterService.getChapterById(id);
        return chapter;
    }

    @PostMapping("/")
    public Chapter saveChapter(@RequestBody Chapter chapter){
        Chapter savedChapter = chapterService.saveChapter(chapter);
        return savedChapter;
    }

    @PutMapping("/")
    public Chapter updateChapter(@RequestBody Chapter chapter){
        Chapter updatedChapter = chapterService.updateChapter(chapter);
        return updatedChapter;
    }

    @DeleteMapping("/{id}")
    public void deleteChapter(@PathVariable int id){
        chapterService.deleteChapter(id);
    }

    @GetMapping("/lesson/{lessonId}")
    public List<Chapter> getChaptersByLessonId(@PathVariable int lessonId){
        List<Chapter> chapters = chapterService.getChaptersByLessonId(lessonId);
        return chapters;
    }
    
}
