package com.wolverineteam.ngpuppies.ServiceTests;

import com.wolverineteam.ngpuppies.data.base.SubscriberRepository;
import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.SubscriberServiceImpl;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SubscriberServiceTest {

    @Mock
    SubscriberRepository mockSubscriberRepository;

    @InjectMocks
    SubscriberServiceImpl SubscriberService;

    @Test
    public void getSubscriberWhenSubscriberIdGiven_ReturnCorrectSubscriber() {
        SubscriberDAO mockSubs = new SubscriberDAO();
        mockSubs.setPhoneNumber("0123456789");

        Mockito.when(mockSubscriberRepository.getSubscriberDAOById("1",1))
                .thenReturn(mockSubs);

        SubscriberDAO result = SubscriberService.getSubscriberDAOById("1",1);

        Assert.assertEquals("0123456789", result.getPhoneNumber());
    }
}
