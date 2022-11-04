package com.messenger.messengerapp.service;

import com.messenger.messengerapp.user.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUser(String username);

    List<User> getAllUsers();

    User deleteUser(User user);
}
