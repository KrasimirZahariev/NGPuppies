package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;

public interface SubscriberService {

    SubscriberDAO getSubscriberById(String id, int bankId);

    SubscriberDAO getAllSubscribersByBankId(int bankId);
}
