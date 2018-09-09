package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.SubscriberRepository;
import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.exception.ForbiddenSubscriberException;
import com.wolverineteam.ngpuppies.exception.SubscriberNotFountException;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
    public Subscriber getSubscriberById(String id) {
        return subscriberRepository.getSubscriberById(id);
    }

    @Override
    public List<SubscriberDAO>  getAllSubscribersByBankId(int bankId) {
        return subscriberRepository.getAllSubscribersByBankId(bankId);
    }

    @Override
    public List<SubscriberDAO> getAllTelecomsSubscribers() {
        return subscriberRepository.getAllTelecomsSubscribers();
    }

    @Override
    public void checkCorrectSubscriberException(String subscriberId, int bankId) throws ForbiddenSubscriberException, SubscriberNotFountException {

        HashSet<String> subs = getAllTelecomsSubscribers().stream()
                .map(SubscriberDAO::getPhoneNumber).collect(Collectors.toCollection(HashSet::new));

        if (!subs.contains(subscriberId)) {
            throw new SubscriberNotFountException("This subscriber does not exist!");

        } else {

            HashSet<String> bankSubs = getAllSubscribersByBankId(bankId).stream()
                    .map(SubscriberDAO::getPhoneNumber).collect(Collectors.toCollection(HashSet::new));

            if (!bankSubs.contains(subscriberId)) {
                throw new ForbiddenSubscriberException("Yod don't have access to these subscribers details!");
            }
        }
    }
}
