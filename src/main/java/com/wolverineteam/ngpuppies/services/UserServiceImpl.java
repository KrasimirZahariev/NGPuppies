package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.utils.JwtSecurityConstants;
import com.wolverineteam.ngpuppies.data.base.RoleRepository;
import com.wolverineteam.ngpuppies.data.base.UserRepository;
import com.wolverineteam.ngpuppies.models.Role;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public void update(User user) {
        User modifiedUser = loadUserByUsername(user.getUsername());

        if (user.getPassword() != null) {
            modifiedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        
        modifiedUser.setUsername(user.getUsername());
        modifiedUser.setEik(user.getEik());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.loadRoleByRoleName(user.getRoles().get(0).getRole()));
        modifiedUser.setRoles(roles);
        userRepository.update(modifiedUser);
    }

    @Override
    public void create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.loadRoleByRoleName(user.getRoles().get(0).getRole()));
        user.setRoles(roles);
        userRepository.create(user);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.loadUserByUsername(username);
    }
}
