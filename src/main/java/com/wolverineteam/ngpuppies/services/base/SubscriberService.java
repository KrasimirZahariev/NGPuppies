package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.models.Subscriber;

public interface SubscriberService {

    Subscriber getSubscriberById(String id, int bankId);

    SubscriberDAO getSubscriberDAOById(String id, int bankId);

    SubscriberDAO getAllSubscribersByBankId(int bankId);
}
