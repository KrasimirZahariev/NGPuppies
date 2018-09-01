package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.data.dto.SubscriberDTO;
import com.wolverineteam.ngpuppies.models.Subscriber;

public interface SubscriberRepository {

    SubscriberDTO getSubscriberById(String subscriberId, int bankId);

    SubscriberDTO getAllSubscribersByBankId(int bankId);
}
