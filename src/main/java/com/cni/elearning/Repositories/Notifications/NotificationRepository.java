package com.cni.elearning.Repositories.Notifications;

import com.cni.elearning.Models.Notifications.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("Select n from Notification n where n.user.id=:userId")
    List<Notification> getNotificationByUserId(@Param("userId") int userId);
}
