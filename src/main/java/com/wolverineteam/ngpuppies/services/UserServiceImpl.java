package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.UserRepository;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void update(String userId, User user) {
        int id = Integer.parseInt(userId);
        userRepository.update(id, user);
    }

    @Override
    public void create(User user) {
        userRepository.create(user);
    }

    @Override
    public void delete(String userId) {
        int id = Integer.parseInt(userId);
        userRepository.delete(id);
    }
}
