package com.cni.elearning.Controllers.Chatting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Services.Chatting.IChatService;

import java.util.List;

import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final IChatService chatService;
    public ChatController(IChatService chatService) {
        this.chatService = chatService;
    }
    @GetMapping("/")
    public ResponseEntity<?>  getAllChats(){
        List<Chat> chats = chatService.getAllChats();
        if (chats.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No chats found");
        }
        else {
            return ResponseEntity.ok(chats);
        }
    }
}
