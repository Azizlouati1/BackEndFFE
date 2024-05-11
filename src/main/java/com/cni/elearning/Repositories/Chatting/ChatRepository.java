package com.cni.elearning.Repositories.Chatting;

import com.cni.elearning.Models.Chatting.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    @Query("select c from Chat c where c.student.id =:studentID and c.instructor.id =:instructorID")
    Chat findChat(@Param("studentID") int studentID, @Param("instructorID") int instructorID);
}
