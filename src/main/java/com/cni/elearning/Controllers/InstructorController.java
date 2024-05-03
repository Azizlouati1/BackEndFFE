package com.cni.elearning.Controllers;

import com.cni.elearning.Models.Instructor;
import com.cni.elearning.Services.IInstructorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/instructors")
public class InstructorController {
    private final IInstructorService instructorService;

    public InstructorController(IInstructorService instructorService) {
        this.instructorService = instructorService;
    }
    @GetMapping("/")
    public List<Instructor> getAllInstructors() {
        return instructorService.findAllInstructors();
    }
    @GetMapping("/{id}")
    public Instructor getInstructorById(@PathVariable int id) {
        return instructorService.findInstructorById2(id);
    }
    @PostMapping("/")
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return instructorService.addInstructor(instructor);
    }
    @DeleteMapping("/{id}")
    public void deleteInstructor(@PathVariable int id) {
        instructorService.deleteInstuctor(id);
    }
    @PutMapping("/{id}")
    public Instructor updateInstructor(@RequestBody Instructor instructor, @PathVariable int id) {
        return instructorService.updateInstructor(instructor, id);
    }

}
