package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.data.dto.UserDTO;
import com.wolverineteam.ngpuppies.exception.*;
import com.wolverineteam.ngpuppies.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService extends UserDetailsService {

    User getById(int id);

    User loadUserByUsername(String username);

    List<User> getAll();

    void update(UserDTO user);

    void create(UserDTO user);

    void deleteUser(int userId);

    void changePassword(UserDTO userDTO);

    void checkForUserDtoExceptions(UserDTO user) throws FieldCantBeNullException, EikCanContainOnlyDigitsException, InvalidRoleException, UsernameInvalidInputException, PasswordInvalidInputException;
}
