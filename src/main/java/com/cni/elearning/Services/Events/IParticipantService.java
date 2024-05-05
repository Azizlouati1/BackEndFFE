package com.cni.elearning.Services.Events;

import com.cni.elearning.Models.Events.Participant;

import java.util.List;

public interface IParticipantService {
    List<Participant> getAllParticipants();

    Participant saveParticipant(Participant participant);

    List<Participant> getParticipantsByEventId(int quizId);

    Participant getParticipantById(int id);

    void deleteParticipant(int id);

    Participant updateParticipant(Participant participant, int id);
}
