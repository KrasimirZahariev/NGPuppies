package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.SubscriberRepository;
import com.wolverineteam.ngpuppies.data.dto.SubscriberDTO;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private SubscriberRepository subscriberRepository;
    private UserService userService;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, UserService userService) {
        this.subscriberRepository = subscriberRepository;
        this.userService = userService;
    }

    public SubscriberDTO getSubscriberById(String subscriberId, HttpServletRequest request) {
        String userName = userService.getUsernameFromToken(request);
        User user = userService.loadUserByUsername(userName);
        int bankId = user.getUserId();
        return subscriberRepository.getSubscriberById(subscriberId, bankId);
    }
}
