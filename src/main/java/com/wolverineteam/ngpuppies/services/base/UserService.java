package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getById(int id);

    User loadUserByUsername(String username);

    List<User> getAll();

    void update(String userId, User user);

    void create(User user);

    void delete(String id);
}
