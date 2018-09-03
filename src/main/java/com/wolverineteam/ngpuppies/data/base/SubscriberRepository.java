package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.models.Subscriber;

import java.util.List;

public interface SubscriberRepository {

    Subscriber getSubscriberById(String subscriberId);

    SubscriberDAO getSubscriberDAOById(String subscriberId, int bankId);

    SubscriberDAO getAllSubscribersByBankId(int bankId);

    List<SubscriberDAO> getAllTelecomsSubscribers();
}
