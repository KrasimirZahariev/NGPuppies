package com.wolverineteam.ngpuppies.ServiceTests;

import com.wolverineteam.ngpuppies.data.base.ServiceRepository;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.services.ServiceServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ServiceServiceTest {

    @Mock
    ServiceRepository serviceRepository;

    @InjectMocks
    ServiceServiceImpl serviceService;

    @Test
    public void getAllervices_ReturnAllCorrectServices(){
        Mockito.when(serviceRepository.getAll()).thenReturn(Arrays.asList(
                new Service(),
                new Service(),
                new Service()));

        List<Service> result = serviceService.getAll();

        Assert.assertEquals(3, result.size());
    }
}
