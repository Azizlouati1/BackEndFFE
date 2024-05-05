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
        // Retrieve the student and event associated with the participant
        Student studentParticipated = studentRepository.findById(participant.getStudent().getId()).orElse(null);
        Event eventParticipated = eventRepository.findById(participant.getEvent().getId()).orElse(null);

        // Check if the student and event exist
        assert studentParticipated != null;
        assert eventParticipated != null;

        // Check if the participant already exists
        Participant participantFound = participantRepository.findByStudentAndEventId(studentParticipated.getId(), eventParticipated.getId());
        if (participantFound != null) {
            throw new RuntimeException("Participant already exists");
        }

        // Get the current time in UTC
        LocalDateTime localDateTime = LocalDateTime.now();
        // Add 1 hour to the current time to match Tunisia's time zone
        localDateTime = localDateTime.plusHours(1);
        // Convert LocalDateTime to Date
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // Set the time deposed for the participant
        participant.setTimeDeposed(currentDate);

        // Check if the current date is within the event's start and end dates
        if (currentDate.before(eventParticipated.getEndDate()) && currentDate.after(eventParticipated.getStartDate())) {
            // Save the participant
            return participantRepository.save(participant);
        } else {
            // Participant's time is not within the event's duration
            throw new RuntimeException("Time of the event ended");
        }
    }


    @Override
    public List<Participant> getParticipantsByEventId(int quizId) {
        return participantRepository.findByEventId(quizId);
    }
    @Override
    public Participant getParticipantById(int id) {
        Optional<Participant> participant = participantRepository.findById(id);
        return participant.get();
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
