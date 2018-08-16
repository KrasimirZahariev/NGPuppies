package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.User;

import java.util.List;

public interface UserRepository {

    User getById(int id);

    List<User> getAll();

    void update(int id, User user);

    void create(User user);

    void delete(int id);
}
