package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.models.Subscriber;

public interface SubscriberRepository {

    Subscriber getSubscriberById(String subscriberId, int bankId);

    SubscriberDAO getSubscriberDAOById(String subscriberId, int bankId);

    SubscriberDAO getAllSubscribersByBankId(int bankId);
}
