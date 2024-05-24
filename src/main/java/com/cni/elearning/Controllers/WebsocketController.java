package com.cni.elearning.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ws")
@RequiredArgsConstructor
public class WebsocketController {

  //  private final SimpMessagingTemplate messagingTemplate;

 //   @MessageMapping("/sendMessage")
   // @SendTo("/topic/messages")
  //  public String broadcastMessage(String message) {
   //     return message;
  //  }

    // Optionally, if you want to send a message to a specific user
  //  @MessageMapping("/sendPrivateMessage")
  //  @SendToUser("/queue/reply")
 //   public String sendPrivateMessage(String message) {
     ///   return message;
   // }
}
