package com.cni.elearning.Repositories.Events;

import com.cni.elearning.Models.Events.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
    @Query("select p from Participant p where p.event.id =:eventId")
    public List<Participant> findByEventId(@Param("eventId") int eventId);
}
