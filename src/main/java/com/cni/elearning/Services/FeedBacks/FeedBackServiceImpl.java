package com.cni.elearning.Services.FeedBacks;

import com.cni.elearning.Models.Cours.Cour;
import com.cni.elearning.Models.FeedBacks.FeedBack;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Repositories.Cours.CourRepository;
import com.cni.elearning.Repositories.FeedBacks.FeedBackRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedBackServiceImpl implements IFeedBackService {

    private final FeedBackRepository feedBackRepository;
    private final CourRepository courRepository;
    private final StudentRepository studentRepository;

    public FeedBackServiceImpl(FeedBackRepository feedBackRepository, CourRepository courRepository, StudentRepository studentRepository) {
        this.feedBackRepository = feedBackRepository;
        this.courRepository = courRepository;
        this.studentRepository = studentRepository;
    }
    @Override
    public List<FeedBack> getAllFeedBacks() {
        return feedBackRepository.findAll();
    }
    @Override
    public FeedBack saveFeedBack(FeedBack feedBack) {
        Student studentSaved = studentRepository.findById(feedBack.getStudent().getId()).orElse(null);
        Cour courSaved = courRepository.findById(feedBack.getCour().getId()).orElse(null);
        assert studentSaved != null;
        assert courSaved != null;
        FeedBack feedbackSaved = feedBackRepository.findByStudentIdAndCourId(studentSaved.getId(), courSaved.getId());
        if (feedbackSaved == null) {
            return feedBackRepository.save(feedBack);
        }
        throw new RuntimeException("Already have a feedback ");
    }

    @Override
    public FeedBack getFeedBackById(int id) {
        Optional<FeedBack> feedBack = feedBackRepository.findById(id);
        return feedBack.get();
    }
    @Override
    public void deleteFeedBack(int id) {
        feedBackRepository.deleteById(id);
    }
    @Override
    public FeedBack updateFeedBack(FeedBack feedBack, int id) {
        Optional<FeedBack> feedBack1 = feedBackRepository.findById(id);
        if(feedBack1.isPresent()) {
            return feedBackRepository.save(feedBack);
        }
        throw new RuntimeException( "FeedBack not found with id "+id);
    }
}
