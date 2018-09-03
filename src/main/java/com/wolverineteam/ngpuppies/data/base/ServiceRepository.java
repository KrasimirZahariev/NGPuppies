package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.Service;

import java.util.List;

public interface ServiceRepository {

    Service loadServiceByServiceName(String serviceName);

    List<Service> getAll();
}
