package com.cni.elearning.Controllers.Cours;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Cours.Chapter;
import com.cni.elearning.Services.Cours.IChapterService;

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

    @PutMapping("/{id}")
    public Chapter updateChapter(@RequestBody Chapter chapter, @PathVariable int id){
        Chapter updatedChapter = chapterService.updateChapter(chapter,id);
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
    @GetMapping("/{id}/name")
    public String getChapterName(@PathVariable int id){
        return chapterService.getChapterNameById(id);
    }
}
