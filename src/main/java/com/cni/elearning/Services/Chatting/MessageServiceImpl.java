package com.cni.elearning.Services.Chatting;

import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Models.Chatting.Message;
import com.cni.elearning.Models.Users.Role;
import com.cni.elearning.Models.Users.User;
import com.cni.elearning.Repositories.Chatting.ChatRepository;
import com.cni.elearning.Repositories.Chatting.MessageRepository;
import com.cni.elearning.Repositories.Users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements IMessageService{
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Override
    public Message sendMessage(Message message) {
        // Retrieve sender, receiver, and chat from repositories
        User userSender = userRepository.findById(message.getSender())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        User userReceiver = userRepository.findById(message.getReceiver())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));
        Chat chat = chatRepository.findById(message.getChat().getId()).orElse(null);
        assert chat != null;

        // Check if sender or receiver is an instructor and the other is a student
        if ((userSender.getRole() == Role.INSTRUCTOR && userReceiver.getRole() == Role.STUDENT) ||
                (userSender.getRole() == Role.STUDENT && userReceiver.getRole() == Role.INSTRUCTOR)) {
            // Check if the chat involves the correct users
            if ((userSender.getId() == chat.getInstructor().getId() && userReceiver.getId() == chat.getStudent().getId()) ||
                    (userSender.getId() == chat.getStudent().getId() && userReceiver.getId() == chat.getInstructor().getId())) {
                return messageRepository.save(message);
            } else {
                System.out.println("Invalid chat participants");
            }
        } else {
            System.out.println("Invalid user roles for messaging");
        }
        throw new RuntimeException("Message not sent");
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }
    @Override
    public Message getMessageById(int id) {
        return messageRepository.findById(id).orElse(null);
    }
    @Override
    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }
    @Override
    public Message updateMessage(Message message, int id) {
        Message messageToUpdate = messageRepository.findById(id).orElse(null);
        if(messageToUpdate != null && message.getId() == messageToUpdate.getId()) {
            messageRepository.save(message);
        }
        throw new RuntimeException("message not found");
    }
    @Override
    public List<Message> getMessagesByChatId(int chatId){
        return messageRepository.findMessagesByChatId(chatId);
    }

}
