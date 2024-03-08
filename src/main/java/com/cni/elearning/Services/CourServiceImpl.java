package com.cni.elearning.Services;

import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Cour;
import com.cni.elearning.Repositories.CourRepository;
import java.util.List;

@Service
public class CourServiceImpl implements ICourService{

    private final CourRepository courRepository;

    public CourServiceImpl(CourRepository courRepository) {
        this.courRepository = courRepository;
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
        return courRepository.findById(id).get();
    }

    @Override
    public void deleteCour(int id) {
        courRepository.deleteById(id);
    }
}
