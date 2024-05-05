package com.cni.elearning.Controllers.Events;


import com.cni.elearning.Models.Events.Event;
import com.cni.elearning.Services.Events.IEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {
    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable int id){

        return eventService.getEventById(id);
    }

    @PostMapping("/")
    public Event saveEvent(@RequestBody Event event){
        return eventService.saveEvent(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable int id){
        eventService.deleteEvent(id);
    }


    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable int id, @RequestBody Event event){
        return eventService.updateEvent(event,id);
    }
}
