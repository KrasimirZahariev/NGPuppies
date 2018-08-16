package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.Subscriber;

public interface SubscriberRepository {

    Subscriber getById(int id);
}
