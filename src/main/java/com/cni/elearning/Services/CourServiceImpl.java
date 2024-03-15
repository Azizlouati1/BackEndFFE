package com.cni.elearning.Services;

import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Cour;
import com.cni.elearning.Repositories.CourRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    Optional<Cour> optionalCour = courRepository.findById(id);
    if (optionalCour.isPresent()) {
        return optionalCour.get();
    } else {
        // Handle the case where the Cour object with the given ID doesn't exist
        // For now, let's throw an exception
        throw new NoSuchElementException("Cour with id " + id + " not found");
    }
}

    @Override
    public void deleteCour(int courseId) {
        Cour cour = courRepository.findById(courseId).orElse(null);
        if (cour != null) {
            List<Cour> recommendedCourses = cour.getRecommendedCourses();
            recommendedCourses.removeIf(course -> course.getId() == courseId);
            courRepository.deleteCourseFromRecommendedCourses(courseId);
        }
    }

    @Override
    public Cour updateCour(Cour cour) {
        return courRepository.save(cour);
    }
}
