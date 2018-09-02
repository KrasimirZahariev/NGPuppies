package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.data.dto.SubscriberDAO;

public interface SubscriberRepository {

    SubscriberDAO getSubscriberById(String subscriberId, int bankId);

    SubscriberDAO getAllSubscribersByBankId(int bankId);
}
