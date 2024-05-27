package com.cni.elearning.Services.Chatting;

import java.util.List;

import com.cni.elearning.Dtos.ChatDTO;
import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Models.Users.Instructor;
import com.cni.elearning.Models.Users.Student;
import com.cni.elearning.Models.Users.User;
import com.cni.elearning.Repositories.Chatting.ChatRepository;
import com.cni.elearning.Repositories.Users.InstructorRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import com.cni.elearning.Repositories.Users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements IChatService {
    private final ChatRepository chatRepository;
    private final View error;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public Chat saveChat(ChatDTO chat) {
        Instructor instructor = instructorRepository.findById(chat.getInstructor()).orElse(null);
        Student student = studentRepository.findById(chat.getStudent()).orElse(null);
        Chat savedChat = chatRepository.findChat(chat.getStudent(), chat.getInstructor());
        Chat chatToSave = new Chat();
        assert instructor != null;
        assert student != null;
        chatToSave.setInstructor(instructor);
        chatToSave.setStudent(student);
        if (savedChat == null) {
            return chatRepository.save(chatToSave);
        }
        throw new RuntimeException("chat already exist");
    }

    @Override
    public Chat updateChat(int id, Chat chat) {
        Chat existingChat = chatRepository.findById(id).orElse(null);
        if (existingChat != null && chat.getId() == existingChat.getId()) {
            existingChat.setMessages(chat.getMessages());
            existingChat.setInstructor(chat.getInstructor());
            existingChat.setStudent(chat.getStudent());
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

    @Override
    public User getUserByChatIdAndSenderId(int chatId, int senderId) {
        User user = userRepository.findById(senderId).orElse(null);
        Chat chat = chatRepository.findById(chatId).orElse(null);
        assert chat != null;
        assert user != null;
        if (chat.getStudent().getId() == user.getId()) {
            return userRepository.findById(chat.getInstructor().getId()).orElse(null);
        } else if (chat.getInstructor().getId() == user.getId()) {
            return userRepository.findById(chat.getStudent().getId()).orElse(null);
        }
        else{
            throw new RuntimeException("Sender not in Chat");
        }
    }
}
