package com.messenger.messengerapp.repository;

import com.messenger.messengerapp.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName (String name);
}
