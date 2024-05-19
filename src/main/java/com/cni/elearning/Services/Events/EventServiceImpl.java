package com.cni.elearning.Services.Events;




import com.cni.elearning.Models.Events.Event;
import com.cni.elearning.Repositories.Events.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EventServiceImpl implements IEventService{
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
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
}
