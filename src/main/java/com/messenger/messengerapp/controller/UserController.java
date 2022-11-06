package com.messenger.messengerapp.controller;

import com.messenger.messengerapp.model.User;
import com.messenger.messengerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }
    @GetMapping("/user-create")
    public String createUserForm (User user) {
        return "user-create";
    }
    @PostMapping("/user-create")
    public String createUser (User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
