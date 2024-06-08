package com.cni.elearning.Controllers.Users;

import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Services.Users.IStudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.findStudentById2(id);
    }
    @PostMapping("/")
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable int id) {
        return studentService.updateStudent(id,student);
    }

}
