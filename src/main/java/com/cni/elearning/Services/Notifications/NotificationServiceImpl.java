package com.cni.elearning.Services.Notifications;

import com.cni.elearning.Models.Notifications.Notification;
import com.cni.elearning.Models.Users.User;
import com.cni.elearning.Repositories.Notifications.NotificationRepository;
import com.cni.elearning.Repositories.Users.StudentRepository;
import com.cni.elearning.Repositories.Users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService{

    private final NotificationRepository notificationRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }
    @Override
    public Notification getNotificationById(int id){
        return notificationRepository.findById(id).get();
    }
    @Override
    public Notification addNotification(int userId,String message){
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        Notification notificationToSave = new Notification();
        notificationToSave.setUser(user);
        notificationToSave.setVue(Boolean.FALSE);
        notificationToSave.setMessage(message);
        return notificationRepository.save(notificationToSave);
    }
    @Override
    public List<Notification> allNotificationTrue(int userId){
        List<Notification> notifications = notificationRepository.getNotificationByUserId(userId);
        for (Notification n : notifications){
            n.setVue(Boolean.TRUE);
            notificationRepository.save(n);
        }
        return notificationRepository.getNotificationByUserId(userId);
    }
    @Override
    public List<Notification> getNotificationsById(int userId){
        return notificationRepository.getNotificationByUserId(userId);
    }
}
