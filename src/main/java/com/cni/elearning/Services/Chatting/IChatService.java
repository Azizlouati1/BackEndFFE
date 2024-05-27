package com.cni.elearning.Services.Chatting;

import java.util.List;

import com.cni.elearning.Dtos.ChatDTO;
import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Models.Users.User;

public interface IChatService {
    List<Chat> getAllChats();


    Chat saveChat(ChatDTO chat);

    Chat updateChat(int id, Chat chat);

    void deleteChat(int id);

    Chat getChatById(int id);

    User getUserByChatIdAndSenderId(int chatId, int senderId);
}
