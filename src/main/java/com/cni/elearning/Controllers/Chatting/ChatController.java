package com.cni.elearning.Controllers.Chatting;

import com.cni.elearning.Dtos.ChatDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cni.elearning.Models.Chatting.Chat;
import com.cni.elearning.Services.Chatting.IChatService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {
    private final IChatService chatService;
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
    @PostMapping("/")
    public Chat  addChat(@RequestBody ChatDTO chat){
        return chatService.saveChat(chat);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getChatById(@PathVariable("id") int id){
        Chat chat = chatService.getChatById(id);
        if (chat != null) {
            return ResponseEntity.ok(chat);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No chat found");
    }
    @PutMapping("/{id}")
    public Chat updateChat(@PathVariable("id") int id, @RequestBody Chat chat){
        return chatService.updateChat(id, chat);
    }
    @DeleteMapping("/{id}")
    public void deleteChat(@PathVariable("id") int id){
        chatService.deleteChat(id);
    }
}
