package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.Service;

public interface ServiceRepository {

    Service loadServiceByServiceName(String serviceName);
}
