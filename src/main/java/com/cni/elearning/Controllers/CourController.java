package com.cni.elearning.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Cour;
import com.cni.elearning.Services.ICourService;

@RestController
@RequestMapping("/api/cours")
public class CourController {

    private final ICourService courService;
    
    public CourController (ICourService courService) {
        this.courService = courService;
    }
    
    @GetMapping("/")
    public List<Cour> getAllCours(){
        List<Cour> cours = courService.getAllCours();
        return cours;
    }

    @GetMapping("/{id}")
    public Cour getCourById(@PathVariable int id){
        Cour cour = courService.getCourById(id);
        return cour;
    }

    @PostMapping("/")
    public Cour saveCour(@RequestBody Cour cour){
        Cour savedCour = courService.saveCour(cour);
        return savedCour;
    }

    @PutMapping("/")
    public Cour updateCour(@RequestBody Cour cour){
        Cour updatedCour = courService.updateCour(cour);
        return updatedCour;
    }

    @DeleteMapping("/{id}")
    public void deleteCour(@PathVariable int id){
        courService.deleteCour(id);
    }

    @GetMapping("/lesson/{lessonId}")
    public Cour getCourByLessonId(@PathVariable int lessonId){
        Cour cour = courService.getCourByLessonId(lessonId);
        return cour;
    }
}
