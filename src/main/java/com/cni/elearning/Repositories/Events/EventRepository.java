package com.cni.elearning.Repositories.Events;

import com.cni.elearning.Models.Events.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
