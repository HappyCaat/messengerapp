package com.messenger.messengerapp.service;
import com.messenger.messengerapp.repository.UserRepository;
import com.messenger.messengerapp.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        log.info("Saving new user {} to the database",user.getName());
        return userRepository.save(user);
    }


    @Override
    public User getUser(String username) {
        log.info("Getting user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    @Override
    public User deleteUser(User user) {
       log.info("Deleting user {}", user.getName());
       return null;
    }
}
