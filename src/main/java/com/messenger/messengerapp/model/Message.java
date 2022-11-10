package com.messenger.messengerapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
}
