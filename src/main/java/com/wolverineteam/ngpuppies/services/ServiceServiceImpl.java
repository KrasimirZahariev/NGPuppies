package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.ServiceRepository;
import com.wolverineteam.ngpuppies.services.base.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<com.wolverineteam.ngpuppies.models.Service> getAll() {
        return serviceRepository.getAll();
    }
}
