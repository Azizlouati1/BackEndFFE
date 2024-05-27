package com.cni.elearning.Services.Events;




import com.cni.elearning.Models.Events.Event;
import com.cni.elearning.Models.Events.Participant;
import com.cni.elearning.Repositories.Events.EventRepository;
import com.cni.elearning.Repositories.Events.ParticipantRepository;
import com.cni.elearning.Services.Levelling.ILevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService{
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final ILevelService levelService;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event getEventById(int id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.get();
    }
    @Override
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }
    @Override
    public Event updateEvent(Event event, int id) {
        Optional<Event> event1 = eventRepository.findById(id);
        if(event1.isPresent()) {
            return eventRepository.save(event);
        }
        throw new RuntimeException( "Event not found with id "+id);
    }
    @Override
    public void setWinner(int id, int participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant with ID " + participantId + " not found"));
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event with ID " + id + " not found"));

        if (!event.getParticipants().contains(participant)) {
            throw new RuntimeException("Participant with ID " + participantId + " does not exist in the event");
        }

        if (event.hasWinner()) {
            throw new RuntimeException("Event with ID " + id + " already has a winner");
        }

        // Setting the winner and updating the participant
        participant.setWinner(true);

        // Adding XP to the participant's student level
        levelService.addXP(event.getReward(), participant.getStudent().getLevel().getId());

        // Saving changes to the repositories
        participantRepository.save(participant);
        eventRepository.save(event);
    }
}
