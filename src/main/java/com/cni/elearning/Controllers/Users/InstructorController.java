package com.cni.elearning.Controllers.Users;

import com.cni.elearning.Models.Users.Instructor;
import com.cni.elearning.Services.Users.IInstructorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
