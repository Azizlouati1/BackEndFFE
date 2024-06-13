package com.cni.elearning.Services.Notifications;

import com.cni.elearning.Models.Notifications.Notification;

import java.util.List;

public interface INotificationService {
    List<Notification> getAllNotifications();

    Notification getNotificationById(int id);

    Notification addNotification(int studentId, String message);

    List<Notification> allNotificationTrue(int studentId);

    List<Notification> getNotificationsById(int userId);
}
