package com.cni.elearning.Repositories.Chatting;

import com.cni.elearning.Models.Chatting.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findMessagesByChatId(int id);
}
