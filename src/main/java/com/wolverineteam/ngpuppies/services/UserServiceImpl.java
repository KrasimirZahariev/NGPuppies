package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.dto.UserDTO;
import com.wolverineteam.ngpuppies.data.base.RoleRepository;
import com.wolverineteam.ngpuppies.data.base.UserRepository;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
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
    public void update(UserDTO user) {
        User modifiedUser = userRepository.getById(Integer.parseInt(user.getUserId()));
        if (user.getPassword() != null) {
            modifiedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        modifiedUser.setUsername(user.getUsername());
        modifiedUser.setEik(user.getEik());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.loadRoleByRoleName(user.getRole()));
        modifiedUser.setRoles(roles);
        userRepository.update(modifiedUser);
    }

    @Override
    public void create(UserDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setEik(user.getEik());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.loadRoleByRoleName(user.getRole()));
        newUser.setRoles(roles);
        userRepository.create(newUser);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.loadUserByUsername(username);
    }
}
