package com.cni.elearning.Services;

import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Cour;
import com.cni.elearning.Repositories.CourRepository;
import com.cni.elearning.Repositories.InstructorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourServiceImpl implements ICourService{

    private final CourRepository courRepository;

    private ILessonService lessonService;

    private final  InstructorRepository instructorRepository;

    public CourServiceImpl(CourRepository courRepository,InstructorRepository instructorRepository) {
        this.courRepository = courRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Cour> getAllCours() {
        return courRepository.findAll();
    }

    @Override
    public Cour saveCour(Cour cour) {
        return courRepository.save(cour);
    }

    @Override
    public Cour getCourByLessonId(int lessonId) {
        return courRepository.findByLessonId(lessonId);
    }

    @Override
    public Cour getCourById(int id) {
        Optional<Cour> optionalCour = courRepository.findById(id);
        if (optionalCour.isPresent()) {
            return optionalCour.get();
        } else {
            throw new NoSuchElementException("Cour with id " + id + " not found");
        }
    }

    @Override
    public void deleteCour(int courseId) {
        courRepository.deleteById(courseId);
    }

    @Override
    public Cour updateCour(Cour cour , int id){
        Optional<Cour> optionalCour = courRepository.findById(id);
        if (optionalCour.isPresent()) {
            return courRepository.save(cour);
        }
        throw new RuntimeException("Cour not found with id: " + id);
    }
    @Override
    public List<Cour> getCourByInstructorId(int id){
        return courRepository.findByInstructorId(id);
    }
    @Override
    public List<Cour> getCourByInstructorId2(int id){
        List<Cour> cours = courRepository.findAll();
        List<Cour> cours2 = new ArrayList<Cour>();
        for (Cour cour : cours) {
            if (cour.getInstructor().getId() == id) {
                cours2.add(cour);
            }
        }
        return cours2;
    }
}
