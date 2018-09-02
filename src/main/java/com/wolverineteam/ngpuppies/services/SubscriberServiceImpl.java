package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.SubscriberRepository;
import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    private SubscriberRepository subscriberRepository;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public SubscriberDAO getSubscriberDAOById(String subscriberId, int bankId) {
        return subscriberRepository.getSubscriberDAOById(subscriberId, bankId);
    }

    @Override
    public Subscriber getSubscriberById(String id, int bankId) {
        return null;
    }

    @Override
    public SubscriberDAO getAllSubscribersByBankId(int bankId) {
        return subscriberRepository.getAllSubscribersByBankId(bankId);
    }
}
