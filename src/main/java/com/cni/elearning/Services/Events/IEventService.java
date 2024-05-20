package com.cni.elearning.Services.Events;

import com.cni.elearning.Models.Events.Event;

import java.util.List;

public interface IEventService {
    List<Event> getAllEvents();

    Event saveEvent(Event event);

    Event getEventById(int id);

    void deleteEvent(int id);

    Event updateEvent(Event event, int id);

    void setWinner(int id, int participantId);
}
