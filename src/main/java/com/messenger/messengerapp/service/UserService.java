package com.messenger.messengerapp.service;

import com.messenger.messengerapp.user.Role;
import com.messenger.messengerapp.user.User;

public interface UserService {
    User saveUser (User user);
    Role saveRole (Role role);
}
