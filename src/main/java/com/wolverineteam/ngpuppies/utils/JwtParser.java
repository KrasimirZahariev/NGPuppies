package com.wolverineteam.ngpuppies.utils;

import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtParser {

    private UserService userService;

    @Autowired
    public JwtParser(UserService userService) {
        this.userService = userService;
    }

    public String getUsernameFromToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        return Jwts.parser()
                .setSigningKey(JwtSecurityConstants.SECRET.getBytes())
                .parseClaimsJws(header.replace("Bearer ", ""))
                .getBody()
                .getSubject();
    }

    public int getBankIdByUsernameFromToken(HttpServletRequest request) {
        String username = getUsernameFromToken(request);
        User user = userService.loadUserByUsername(username);

        return user.getUserId();
    }
}
