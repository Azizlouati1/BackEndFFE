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

    @PostMapping("/")
    public Message sendMessage(@RequestBody Message message) {
        // Process and send the message using HTTP endpoint
        messagingTemplate.convertAndSend("/topic/messages", message);
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

    @GetMapping("/chat/{chatId}")
    public List<Message> getMessagesByChatId(@PathVariable int chatId) {
        return messageService.getMessagesByChatId(chatId);
    }

    @MessageMapping("/sendMessage")
    public void handleWebSocketMessage(Message message) {
        // Process and send the message using WebSocket endpoint
        messagingTemplate.convertAndSend("/topic/messages", message);
        messageService.sendMessage(message);
    }
}
