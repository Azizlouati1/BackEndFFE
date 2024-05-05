package com.cni.elearning.Controllers.Events;


import com.cni.elearning.Models.Events.Participant;
import com.cni.elearning.Services.Events.IParticipantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/participants")
public class ParticipantController {
    private final IParticipantService participantService;

    public ParticipantController(IParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/")
    public List<Participant> getAllParticipants(){
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public Participant getParticipantById(@PathVariable int id){

        return participantService.getParticipantById(id);
    }

    @PostMapping("/")
    public Participant saveParticipant(@RequestBody Participant participant){
        return participantService.saveParticipant(participant);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable int id){
        participantService.deleteParticipant(id);
    }


    @PutMapping("/{id}")
    public Participant updateParticipant(@PathVariable int id, @RequestBody Participant participant){
        return participantService.updateParticipant(participant,id);
    }

    @GetMapping("/event/{id}")
    public List<Participant> getParticipantEvents(@PathVariable int id){
        return participantService.getParticipantsByEventId(id);
    }
}

