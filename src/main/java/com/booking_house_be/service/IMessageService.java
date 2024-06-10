package com.booking_house_be.service;

import com.booking_house_be.entity.Message;

import java.util.List;

public interface IMessageService {
    List<Message> findAllBySenderIdAndReceiverId(int senderId, int receiverId);
    Message save(Message message);
    int countUnreadMessagesByReceiverId(int receiverId);
    int countUnreadMessagesByAccountLoginIdAndSenderId(int accountLoginId, int senderId);
    void changeStatusMessage(int senderId, int receiverId);
}
