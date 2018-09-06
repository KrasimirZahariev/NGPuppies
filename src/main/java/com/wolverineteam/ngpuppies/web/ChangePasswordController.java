package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.data.dto.PasswordDTO;
import com.wolverineteam.ngpuppies.data.dto.UserDTO;
import com.wolverineteam.ngpuppies.services.base.UserService;
import com.wolverineteam.ngpuppies.utils.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/change-password/")
public class ChangePasswordController {

    private UserService userService;
    private JwtParser jwtParser;

    @Autowired
    public ChangePasswordController(UserService userService, JwtParser jwtParser) {
        this.userService = userService;
        this.jwtParser = jwtParser;
    }

    @PostMapping
    public void changePassword(@RequestBody PasswordDTO passwordDTO, HttpServletRequest request) {
        String username = jwtParser.getUsernameFromToken(request);
        String  password= passwordDTO.getPassword();

        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(password);
        userDTO.setUsername(username);

        userService.changePassword(userDTO);
    }
}
