package com.cni.elearning.Services;

import com.cni.elearning.Models.Instructor;
import com.cni.elearning.Models.Role;
import com.cni.elearning.Repositories.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InstructorServiceImpl implements IInstructorService {
    public final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> findAllInstructors(){
        return instructorRepository.findAll();
    }
    @Override
    public Optional<Instructor> findInstructorById(int id){
        return instructorRepository.findById(id);
    }

    @Override
    public Instructor addInstructor(Instructor instructor){
        instructor.setRole(Role.INSTRUCTOR);
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor findInstructorById2(int id){
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if(instructor.isPresent()){
            return instructor.get();
        }
        return null;
    }
    @Override
    public void deleteInstuctor(int id){
        instructorRepository.deleteById(id);
    }
}
