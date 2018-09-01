package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.data.dto.SubscriberDTO;
import com.wolverineteam.ngpuppies.models.Subscriber;

import javax.servlet.http.HttpServletRequest;

public interface SubscriberService {

    SubscriberDTO getSubscriberById(String id, int bankId);

    SubscriberDTO getAllSubscribersByBankId(int bankId);
}
