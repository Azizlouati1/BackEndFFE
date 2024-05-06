package com.cni.elearning.Controllers.Progresses;


import com.cni.elearning.Models.Progress.Progress;
import com.cni.elearning.Services.Progresses.IProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/progresses")
public class ProgressController {
    private final IProgressService progressService;

    public ProgressController(IProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/")
    public List<Progress> getAllProgresses(){
        return progressService.getAllProgresss();
    }

    @GetMapping("/{id}")
    public Progress getProgressById(@PathVariable int id){

        return progressService.getProgressById(id);
    }

    @PostMapping("/")
    public Progress saveProgress(@RequestBody Progress progress){
        return progressService.saveProgress(progress);
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable int id){
        progressService.deleteProgress(id);
    }


    @PutMapping("/{id}")
    public Progress updateProgress(@PathVariable int id, @RequestBody Progress progress){
        return progressService.updateProgress(progress,id);
    }
}
