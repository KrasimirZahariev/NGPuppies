package com.wolverineteam.ngpuppies.ServiceTests;

import com.wolverineteam.ngpuppies.data.base.SubscriberRepository;
import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.SubscriberServiceImpl;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SubscriberServiceTest {

    @Mock
    SubscriberRepository mockSubscriberRepository;

    @InjectMocks
    SubscriberServiceImpl subscriberService;

    @Test
    public void getSubscriberDAOWhenSubscriberIdGiven_ReturnCorrectSubscriber() {
        SubscriberDAO mockSubs = new SubscriberDAO();
        mockSubs.setPhoneNumber("0123456789");

        Mockito.when(mockSubscriberRepository.getSubscriberDAOById("1", 1))
                .thenReturn(mockSubs);

        SubscriberDAO result = subscriberService.getSubscriberDAOById("1", 1);

        Assert.assertEquals("0123456789", result.getPhoneNumber());
    }

    @Test
    public void getSubscriberById_ReturnCorrectSubscriber() {
        Subscriber mockSubs = new Subscriber();
        mockSubs.setPhoneNumber("0123456789");
        mockSubs.setFirstName("MockUser");
        mockSubs.setLastName("mockUser");
        mockSubs.setBank(new User());
        mockSubs.setEgn("0123456789");

        Mockito.when(mockSubscriberRepository.getSubscriberById("0123456789"))
                .thenReturn(mockSubs);

        Subscriber result = subscriberService.getSubscriberById("0123456789");

        org.junit.Assert.assertEquals("0123456789", result.getPhoneNumber());
    }

    @Test
    public void getAllSubscribersByBankId_ReturnCorrectSubscribers() {
        Mockito.when(mockSubscriberRepository.getAllSubscribersByBankId(1)).thenReturn(Arrays.asList(
                new SubscriberDAO(),
                new SubscriberDAO(),
                new SubscriberDAO()
        ));

        List<SubscriberDAO> result = subscriberService.getAllSubscribersByBankId(1);

        org.junit.Assert.assertEquals(3, result.size());
    }

    @Test
    public void getAllTelecomsSubscribers_ReturnAllSubscribers() {
        Mockito.when(mockSubscriberRepository.getAllTelecomsSubscribers()).thenReturn(Arrays.asList(
                new SubscriberDAO(),
                new SubscriberDAO(),
                new SubscriberDAO()
        ));

        List<SubscriberDAO> result = subscriberService.getAllTelecomsSubscribers();

        org.junit.Assert.assertEquals(3, result.size());
    }
}
