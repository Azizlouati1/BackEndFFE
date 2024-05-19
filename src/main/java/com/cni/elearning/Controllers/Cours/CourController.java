package com.cni.elearning.Controllers.Cours;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Services.Cours.ICourService;

@RestController
@RequestMapping("/api/cours")
public class CourController {

    private final ICourService courService;
    
    public CourController (ICourService courService) {
        this.courService = courService;
    }
    
    @GetMapping("/")
    public List<Cour> getAllCours(){
        return courService.getAllCours();
    }

    @GetMapping("/{id}")
    public Cour getCourById(@PathVariable int id){
        return courService.getCourById(id);
    }

    @PostMapping("/")
    public Cour saveCour(@RequestBody Cour cour){
        return courService.saveCour(cour);
    }

    @PutMapping("/{id}")
    public Cour updateCour(@RequestBody Cour cour , @PathVariable int id){
        return courService.updateCour(cour,id);
    }

    @DeleteMapping("/{id}")
    public void deleteCour(@PathVariable int id){
        courService.deleteCour(id);
    }

    @GetMapping("/lesson/{lessonId}")
    public Cour getCourByLessonId(@PathVariable int lessonId){
        return courService.getCourByLessonId(lessonId);
    }
    @GetMapping("/instructorid/{id}")
    public List<Cour> getCourByInstructorId(@PathVariable int id){
        return courService.getCourByInstructorId2(id);
    }
    @GetMapping("/search")
    public List<Cour> searchCoursByTitle(@RequestParam String title) {
        return courService.searchCoursByTitle(title);
    }
}
