package com.cni.elearning.Services.Users;


import ch.qos.logback.classic.LoggerContext;
import com.cni.elearning.Models.Levelling.Level;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Models.Users.Role;
import com.cni.elearning.Repositories.Levelling.LevelRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
        public Student findStudentById(int id){
            return studentRepository.findById(id).orElse(null);
        }

        @Override
        public Student addStudent(Student student){
            student.setRole(Role.STUDENT);
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            studentRepository.save(student);
            String emailSend = student.getEmail();
            Student studentSaved = studentRepository.findByEmail(emailSend);
            Level level = new Level();
            int StudentId = studentSaved.getId();
            level.setStudent(StudentId);
            levelRepository.save(level);
            return studentRepository.findById(StudentId).orElse(null);
        }

        @Override
        public Student findStudentById2(int id){
            return studentRepository.findById(id).orElse(null);
        }
        @Override
        public void deleteInstructor(int id){
            studentRepository.deleteById(id);
        }
    @Override
    public Student updateStudent(int id, Student student){
        Student optionalStudent = studentRepository.findById(id).orElse(null);
        if(optionalStudent != null){
            student.setRole(Role.STUDENT);
            if(!Objects.equals(student.getPassword(), optionalStudent.getPassword())) {
                student.setPassword(passwordEncoder.encode(student.getPassword()));
            }
            return studentRepository.save(student);

        }
        throw new RuntimeException("Student not found with id " + id);
    }
    @Override
    @Scheduled(fixedRate = 60000)
    public void checkIfPremium() {
            List<Student> students = studentRepository.findAll();
            for (Student student : students) {
                if (student.getDateEnd() == null || student.getDateEnd().isBefore(LocalDateTime.now())) {
                    student.setIsSubscribed(false);
                    studentRepository.save(student);
                }
            }
    }
    }


