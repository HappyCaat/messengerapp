package com.messenger.messengerapp.repository;

import com.messenger.messengerapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
