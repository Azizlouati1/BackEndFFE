package com.cni.elearning.Services.Chatting;

import java.util.List;

import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Repositories.Chatting.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements IChatService{
    private final ChatRepository chatRepository;
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }
    @Override
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }
    @Override
    public Chat updateChat(int id, Chat chat) {
        Chat existingChat = chatRepository.findById(id).orElse(null);
        if (existingChat != null) {
            existingChat.setMessages(chat.getMessages());
            existingChat.setInstructor(chat.getInstructor().getId());
            existingChat.setStudent(chat.getStudent().getId());
            return chatRepository.save(existingChat);
        } else {
            return null;
        }
    }
    @Override
    public void deleteChat(int id) {
        chatRepository.deleteById(id);
    }
    @Override
    public Chat getChatById(int id) {
        return chatRepository.findById(id).orElse(null);
    }

}
