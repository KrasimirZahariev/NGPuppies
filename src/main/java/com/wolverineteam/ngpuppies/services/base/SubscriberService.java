package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Subscriber;

import javax.servlet.http.HttpServletRequest;

public interface SubscriberService {

    Subscriber getSubscriberById(String id, HttpServletRequest request);
}
