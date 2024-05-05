package com.cni.elearning.Services.Events;

import com.cni.elearning.Models.Events.Event;
import com.cni.elearning.Models.Events.Participant;
import com.cni.elearning.Repositories.Events.ParticipantRepository;
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

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }
    @Override
    public Participant saveParticipant(Participant participant) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        participant.setTimeDeposed(currentDate);
        Event event = participant.getEvent();
        if (event != null && event.getEndDate() != null && currentDate.before(event.getEndDate()) && event.getStartDate() != null && currentDate.after(event.getStartDate()))
                 {
            participant.setWinner(true);
            System.out.println(participant.getTimeDeposed());

        }
        else{
            participant.setWinner(false);
        }
        return participantRepository.save(participant);
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
