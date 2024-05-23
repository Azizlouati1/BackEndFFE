package com.cni.elearning.Controllers.Cours;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Cours.Chapter;
import com.cni.elearning.Services.Cours.IChapterService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    private final IChapterService chapterService;

    public ChapterController(IChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/")
    public List<Chapter> getAllChapters(){
        return chapterService.getAllChapters();
    }

    @GetMapping("/{id}")
    public Chapter getChapterById(@PathVariable int id){
        return chapterService.getChapterById(id);
    }

    @PostMapping("/")
    public Chapter saveChapter(@RequestBody Chapter chapter) {
        return chapterService.saveChapter(chapter);
    }
    @PutMapping("/{id}")
    public Chapter updateChapter(@RequestBody Chapter chapter, @PathVariable int id){
        return chapterService.updateChapter(chapter, id);
    }

    @DeleteMapping("/{id}")
    public void deleteChapter(@PathVariable int id){
        chapterService.deleteChapter(id);
    }

    @GetMapping("/lesson/{lessonId}")
    public List<Chapter> getChaptersByLessonId(@PathVariable int lessonId){
        return chapterService.getChaptersByLessonId(lessonId);
    }

    @GetMapping("/{id}/name")
    public String getChapterName(@PathVariable int id){
        return chapterService.getChapterNameById(id);
    }
    @PostMapping(value = "/convert-html")
    public ResponseEntity<String> convertToHtml(@RequestParam MultipartFile file) throws IOException {
        String html = generateHtmlFromPdf(file.getInputStream());
        return ResponseEntity.ok(html);
    }
    public static String generateHtmlFromPdf(InputStream inputStream) throws IOException {
        PDDocument pdf = PDDocument.load(inputStream);
        PDFDomTree parser = new PDFDomTree();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Writer output = new PrintWriter(baos, true, StandardCharsets.UTF_8);
        parser.writeText(pdf, output);
        output.close();
        pdf.close();
        return baos.toString(StandardCharsets.UTF_8);
    }
}
