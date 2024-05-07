package com.cni.elearning.Controllers.Levelling;


import com.cni.elearning.Models.Levelling.Level;
import com.cni.elearning.Services.Levelling.ILevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/levels")
public class LevelController {
    private final ILevelService levelService;

    public LevelController(ILevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping("/")
    public List<Level> getAllLevels(){
        return levelService.getAllLevels();
    }

    @GetMapping("/{id}")
    public Level getLevelById(@PathVariable int id){

        return levelService.getLevelById(id);
    }

    @PostMapping("/")
    public Level saveLevel(@RequestBody Level level){
        return levelService.saveLevel(level);
    }

    @DeleteMapping("/{id}")
    public void deleteLevel(@PathVariable int id){
        levelService.deleteLevel(id);
    }


    @PutMapping("/{id}")
    public Level updateLevel(@PathVariable int id, @RequestBody Level level){
        return levelService.updateLevel(level,id);
    }
}
