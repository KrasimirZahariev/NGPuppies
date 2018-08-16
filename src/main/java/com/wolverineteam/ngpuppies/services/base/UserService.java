package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.User;

import java.util.List;

public interface UserService {

    User getById(int id);

    List<User> getAll();

    void update(int id, User user);

    void create(User user);

    void delete(int id);
}
