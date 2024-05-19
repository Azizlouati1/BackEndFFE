package com.cni.elearning.Services.Events;

import com.cni.elearning.Models.Events.Event;
import com.cni.elearning.Models.Events.Participant;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Repositories.Events.EventRepository;
import com.cni.elearning.Repositories.Events.ParticipantRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantServiceImpl implements IParticipantService {
    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;
    private final StudentRepository studentRepository;
    public ParticipantServiceImpl(ParticipantRepository participantRepository, EventRepository eventRepository, StudentRepository studentRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
        this.studentRepository = studentRepository;
    }
    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }
    @Override
    public Participant saveParticipant(Participant participant) {
        Student studentParticipated = studentRepository.findById(participant.getStudent().getId()).orElse(null);
        Event eventParticipated = eventRepository.findById(participant.getEvent().getId()).orElse(null);
        assert studentParticipated != null;
        assert eventParticipated != null;
        Participant participantFound = participantRepository.findByStudentAndEventId(studentParticipated.getId(), eventParticipated.getId());
        if (participantFound != null) {
            throw new RuntimeException("Participant already exists");
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusHours(1);
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        participant.setTimeDeposed(currentDate);
        if (currentDate.before(eventParticipated.getEndDate()) && currentDate.after(eventParticipated.getStartDate())) {
            return participantRepository.save(participant);
        } else {
            throw new RuntimeException("Time of the event ended");
        }
    }
    @Override
    public List<Participant> getParticipantsByEventId(int quizId) {
        return participantRepository.findByEventId(quizId);
    }
    @Override
    public Participant getParticipantById(int id) {
        return  participantRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteParticipant(int id) {
        participantRepository.deleteById(id);
    }
    @Override
    public Participant updateParticipant(Participant participant, int id) {
        Optional<Participant> participant1 = participantRepository.findById(id);
        if(participant1.isPresent()) {
            return participantRepository.save(participant);
        }
        throw new RuntimeException( "Participant not found with id "+id);
    }
}
