package com.messenger.messengerapp.service;

import com.messenger.messengerapp.model.Message;
import com.messenger.messengerapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessageInTable (Message message) {
        return messageRepository.save(message);
    }
}
