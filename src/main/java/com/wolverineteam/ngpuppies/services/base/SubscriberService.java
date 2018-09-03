package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.models.Subscriber;

import java.util.List;

public interface SubscriberService {

    Subscriber getSubscriberById(String id);

    SubscriberDAO getSubscriberDAOById(String id, int bankId);

    SubscriberDAO getAllSubscribersByBankId(int bankId);

    List<SubscriberDAO> getAllTelecomsSubscribers();
}
