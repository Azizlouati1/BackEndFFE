package com.cni.elearning.Services.Chatting;

import com.cni.elearning.Models.Chatting.Message;

import java.util.List;

public interface IMessageService {
    Message sendMessage(Message message);

    List<Message> getMessages();

    Message getMessageById(int id);

    void deleteMessage(int id);

    Message updateMessage(Message message, int id);
}
