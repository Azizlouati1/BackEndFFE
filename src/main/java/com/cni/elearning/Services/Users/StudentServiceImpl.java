package com.cni.elearning.Services.Users;


import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Models.Users.Role;
import com.cni.elearning.Repositories.Levelling.LevelRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentServiceImpl implements IStudentService {
    
        public final StudentRepository studentRepository;
        private final PasswordEncoder passwordEncoder;
    private final LevelRepository levelRepository;

    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder, LevelRepository levelRepository) {
            this.studentRepository = studentRepository;
            this.passwordEncoder = passwordEncoder;
        this.levelRepository = levelRepository;
    }

        @Override
        public List<Student> findAllStudents(){
            return studentRepository.findAll();
        }
        @Override
        public Optional<Student> findStudentById(int id){
            return studentRepository.findById(id);
        }

        @Override
        public Student addStudent(Student student){
            student.setRole(Role.STUDENT);
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            return studentRepository.save(student);
        }

        @Override
        public Student findStudentById2(int id){
            Optional<Student> student = studentRepository.findById(id);
            if(student.isPresent()){
                return student.get();
            }
            return null;
        }
        @Override
        public void deleteInstuctor(int id){
            studentRepository.deleteById(id);
        }
    @Override
    public Student updateStudent(int id, Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            student.setRole(Role.STUDENT);
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            return studentRepository.save(student);

        }
        throw new RuntimeException("Student not found with id " + id);
    }
    }


