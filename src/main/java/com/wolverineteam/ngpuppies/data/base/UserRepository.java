package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.User;

import java.util.List;

public interface UserRepository {

    User getById(int id);

    List<User> getAll();

    void update(int id, User currency);

    void create(User currency);

    void delete(int id);
}
