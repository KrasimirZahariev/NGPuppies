package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.dto.UserDTO;
import com.wolverineteam.ngpuppies.data.base.RoleRepository;
import com.wolverineteam.ngpuppies.data.base.UserRepository;
import com.wolverineteam.ngpuppies.exception.*;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.RoleService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
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
        User oldUser = userRepository.getById(Integer.parseInt(user.getUserId()));

        if (!oldUser.getRoles().get(0).getRole().equals(user.getRole())) {
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.loadRoleByRoleName(user.getRole()));
            oldUser.setRoles(roles);
        }

        if (user.getPassword() != null) {
            oldUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        oldUser.setUsername(user.getUsername());
        oldUser.setEik(user.getEik());
        userRepository.update(oldUser);
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
    public void deleteUser(int userId) {
        User user = userRepository.getById(userId);
        user.setEnabled(false);
        userRepository.update(user);
    }

    @Override
    public void changePassword(UserDTO userDTO) {
        User user = loadUserByUsername(userDTO.getUsername());

        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.loadRoleByRoleName("ROLE_ADMIN"));
        user.setRoles(roles);

        userRepository.update(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.loadUserByUsername(username);
    }

    @Override
    public void checkForUserDtoExceptions(UserDTO user) throws FieldCantBeNullException, EikCanContainOnlyDigitsException, InvalidRoleException, UsernameInvalidInputException, PasswordInvalidInputException {


        if (user.getUsername().equals("")) {
            throw new FieldCantBeNullException("Username can't be null!");
        }

        for (int i = 0; i < user.getUsername().length(); i++) {
            if (!(Character.isDigit(user.getUsername().charAt(i)) || Character.isAlphabetic(user.getUsername().charAt(i)))) {

                throw new UsernameInvalidInputException("The Username can contain only numeric and alphabetic symbols!");
            }
        }

        if (user.getPassword().equals("")) {
            throw new FieldCantBeNullException("Password can't be null!");
        }

        for (int i = 0; i < user.getPassword().length(); i++) {
            if (!((Character.isDigit(user.getPassword().charAt(i)) || Character.isAlphabetic(user.getPassword().charAt(i))))) {
                throw new PasswordInvalidInputException("The Password can contain only numeric and alphabetic symbols!");
            }
        }

        if (user.getEik().equals("")) {
            throw new FieldCantBeNullException("Eik can't be null!");

        }

        for (int i = 0; i < user.getEik().length(); i++) {
            if (!(Character.isDigit(user.getEik().charAt(i)))) {
                throw new EikCanContainOnlyDigitsException("Eik number can contain only digits!");
            }
        }

        if (user.getRole().equals("")) {
            throw new FieldCantBeNullException("Role can't be null!");
        }

        HashSet<String> roles = roleService.getAllRoles().stream()
                .map(Role::getRole).collect(Collectors.toCollection(HashSet::new));
        if (!roles.contains(user.getRole())) {
            throw new InvalidRoleException("Invalid role!");
        }
    }
}
