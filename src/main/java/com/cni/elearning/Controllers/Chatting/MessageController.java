package com.cni.elearning.Controllers.Chatting;

import com.cni.elearning.Models.Chatting.Message;
import com.cni.elearning.Services.Chatting.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final IMessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;
    @GetMapping("/")
    public List<Message> getMessages() {
        return messageService.getMessages();
    }
    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable int id) {
        return messageService.getMessageById(id);
    }
    @MessageMapping("/sendMessage")
    public Message sendMessage(@RequestBody Message message) {
        // Convert and send the message to a specific user's destination
        messagingTemplate.convertAndSendToUser(
                String.valueOf(message.getReceiver()), // User ID to send the message to
                "/queue/messages", // Destination for the message
                message // The message object to send
        );
        // Optionally, you can also process the message and return it
        return messageService.sendMessage(message);
    }
    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message message) {
        return messageService.updateMessage(message, id);
    }
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
    }
}
