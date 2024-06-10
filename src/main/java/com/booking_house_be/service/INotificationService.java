package com.booking_house_be.service;

import com.booking_house_be.entity.Notification;

import java.util.List;

public interface INotificationService {
    int countUnreadNotifyByAccountLoginId(int accountId);
    List<Notification> findAllByReceiverId(int accountId);
    void changeStatusNotify(int accountId);
    Notification save(Notification notification);
}
