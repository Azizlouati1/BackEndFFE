package com.cni.elearning.Repositories.Chatting;

import com.cni.elearning.Models.Chatting.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    ChatRepository extends JpaRepository<Chat, Integer> {
}
