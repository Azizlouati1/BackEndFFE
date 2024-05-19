package com.cni.elearning.Services.Progresses;

import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Models.Cours.Lesson;
import com.cni.elearning.Models.Progress.Progress;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Repositories.Cours.CourRepository;
import com.cni.elearning.Repositories.Cours.LessonRepository;
import com.cni.elearning.Repositories.Progresses.ProgressRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import com.cni.elearning.Services.Levelling.ILevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements IProgressService{
    private final ProgressRepository progressRepository;
    private final CourRepository courRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final ILevelService levelService;
    private final int XPForEveryLesson = 1000;

    @Override
    public List<Progress> getAllProgresss() {
        return progressRepository.findAll();
    }
    @Override
    public Progress saveProgress(Progress progress) {
        Student studentSaved = studentRepository.findById(progress.getStudent().getId()).orElse(null);
        Cour courSaved = courRepository.findById(progress.getCour().getId()).orElse(null);
        assert studentSaved != null;
        assert courSaved != null;
        Progress progressSaved = progressRepository.findByStudentIdAndCourId(studentSaved.getId(), courSaved.getId());
        if (progressSaved == null && (courSaved.getPremium()==studentSaved.getIsSubscribed()||studentSaved.getIsSubscribed()) ) {
            int Lessons = courSaved.getLessons().size();
            progress.setMaxXP(Lessons*XPForEveryLesson);
            return progressRepository.save(progress);
        }
        throw new RuntimeException("Already have a progress Saved ");
    }

    @Override
    public Progress getProgressById(int id) {
        Optional<Progress> progress = progressRepository.findById(id);
        return progress.get();
    }
    @Override
    public void deleteProgress(int id) {
        progressRepository.deleteById(id);
    }
    @Override
    public Progress updateProgress(Progress progress, int id) {
        Progress progressSaved = progressRepository.findById(id).orElse(null);
        if(progressSaved != null && progressSaved.getId() == progress.getId()) {
            progress.setCour(progressSaved.getCour().getId());
            progress.setStudent(progressSaved.getStudent().getId());
            return progressRepository.save(progress);
        }
        throw new RuntimeException( "Progress not found with id "+id);
    }
    @Override
    public Progress AddLessonToProgress(int id , int lessonId) {
        Progress progress = progressRepository.findById(id).orElse(null);
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        assert progress != null;
        assert lesson != null;
        List<Lesson> lessons = progress.getCompletedLessons();
        if (progress.getCour().getLessons().stream().anyMatch(l -> l == lesson)) {
            for (Lesson lessonFound : lessons) {
                if (lessonFound == lesson) {
                    throw new RuntimeException("lesson Already added");
                }
            }
            lessons.add(lesson);
            progress.setCompletedLessons(lessons);
            totalLessons(progress);
            return progressRepository.save(progress);
        } else if (progress.getCourEnded()) {
            throw new RuntimeException("Progress Already ended");

        } else{
            throw new RuntimeException("lesson not in the cour");
        }
    }
    @Override
    public void updateLessons(){
        List<Progress> progresses = progressRepository.findAll();
        for (Progress progress : progresses){
            totalLessons(progress);
            progress.setMaxXP(progress.getCour().getLessons().size()*XPForEveryLesson);
            progressRepository.save(progress);
        }
    }

    private void totalLessons(Progress progress) {
        int totalLessons = progress.getCour().getLessons().size();
        int completedLessons = progress.getCompletedLessons().size();
        BigDecimal percentage = BigDecimal.valueOf((double) completedLessons / totalLessons * 100);
        progress.setPercentage(percentage);
        progress.setCourEnded(progress.getPercentage().compareTo(BigDecimal.valueOf(100)) == 0);
        if(progress.getCourEnded()){
            levelService.addXP((progress.getMaxXP()-progress.getXPReceived()),(progress.getStudent().getLevel().getId()));
            progress.setXPReceived(progress.getMaxXP());
            progressRepository.save(progress);
        }
    }
}
