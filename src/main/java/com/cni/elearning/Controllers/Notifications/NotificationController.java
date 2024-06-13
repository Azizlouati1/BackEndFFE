package com.cni.elearning.Controllers.Notifications;

import com.cni.elearning.Models.Notifications.Notification;
import com.cni.elearning.Services.Notifications.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/notifications")
public class NotificationController {
    private final INotificationService notificationService;

    @GetMapping("/")
    public List<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable int id){
        return notificationService.getNotificationById(id);
    }
    @PutMapping("/{id}")
    public List<Notification> AllNotificationTrue(@PathVariable int id){
        return notificationService.allNotificationTrue(id);
    }
    @GetMapping("/user/{userId}")
    public List<Notification> getNotificationByUserId(@PathVariable int userId){
        return notificationService.getNotificationsById(userId);
    }
}
