package com.cni.elearning.Services;

import com.cni.elearning.Models.Lesson;
import com.cni.elearning.Repositories.LessonRepository;
import org.springframework.stereotype.Service;

import com.cni.elearning.Models.Cour;
import com.cni.elearning.Repositories.CourRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourServiceImpl implements ICourService{

    private final CourRepository courRepository;

    private ILessonService lessonService;

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
        Optional<Cour> optionalCour = courRepository.findById(courseId);

        if (optionalCour.isPresent()) {
            Cour cour = optionalCour.get();

            // Remove the course from recommendedCourses of other courses
            List<Cour> recommendedCourses = cour.getRecommendedCourses();
            recommendedCourses.forEach(recommendedCourse -> {
                recommendedCourse.getRecommendedCourses().removeIf(course -> course.getId() == courseId);
            });

            // Save the updated recommendedCourses
            courRepository.saveAll(recommendedCourses);
            List<Lesson> lessonIds = cour.getLessons();
            lessonIds.forEach(lesson -> {
                lessonService.deleteLesson(lesson.getId());
            });

            // Delete the course
            courRepository.delete(cour);
        } else {
            // Handle case where the course with courseId is not found
            throw new RuntimeException("Course not found with ID: " + courseId);
        }
    }

    @Override
    public Cour updateCour(Cour cour) {
        return courRepository.save(cour);
    }
}
