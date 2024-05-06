package com.cni.elearning.Services.Progresses;

import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Models.Progress.Progress;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Repositories.Cours.CourRepository;
import com.cni.elearning.Repositories.Progresses.ProgressRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProgressServiceImpl implements IProgressService{
    private final ProgressRepository progressRepository;
    private final CourRepository courRepository;
    private final StudentRepository studentRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository, CourRepository courRepository, StudentRepository studentRepository) {
        this.progressRepository = progressRepository;
        this.courRepository = courRepository;
        this.studentRepository = studentRepository;
    }
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
        if (progressSaved == null) {
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
}
