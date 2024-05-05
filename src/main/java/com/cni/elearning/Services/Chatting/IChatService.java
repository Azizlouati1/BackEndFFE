package com.cni.elearning.Services.Chatting;

import java.util.List;

import com.cni.elearning.Models.Chatting.Chat;

public interface IChatService {
    List<Chat> getAllChats();

    Chat saveChat(Chat chat);

    Chat updateChat(int id, Chat chat);

    void deleteChat(int id);

    Chat getChatById(int id);
}
