package com.messenger.messengerapp.controller;

import com.messenger.messengerapp.model.Message;
import com.messenger.messengerapp.model.User;
import com.messenger.messengerapp.service.MessageService;
import com.messenger.messengerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
 //   private final UserService userService;
    private final MessageService messageService;
    @Autowired
    public Controller(UserService userService, MessageService messageService) {
 //       this.userService = userService;
        this.messageService = messageService;
    }
    @GetMapping("/message-create")
    public String createMessageForm (Message message) {
        return "message-create";
    }
    @GetMapping("/message-create")
    public String createMessage (Message message) {
        messageService.createMessageInTable(message);
        return "redirect:/messages";
    }

//    @GetMapping("/users")
//    public String findAll(Model model) {
//        List<User> users = userService.findAll();
//        model.addAttribute("users", users);
//        return "user-list";
//    }
//    @GetMapping("/user-create")
//    public String createUserForm (User user) {
//        return "user-create";
//    }
//    @PostMapping("/user-create")
//    public String createUser (User user) {
//        userService.saveUser(user);
//        return "redirect:/users";
//    }
}
