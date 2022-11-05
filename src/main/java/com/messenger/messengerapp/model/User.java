package com.messenger.messengerapp.model;

import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String login;
    @Column(name = "user_password")
    private String password;

}
