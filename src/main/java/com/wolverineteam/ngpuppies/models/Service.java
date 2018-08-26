package com.wolverineteam.ngpuppies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceID")
    private int serviceId;

    @NotNull
    @Column(name = "Service")
    private String service;

    @JsonIgnore
    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Bill> bills;

    public Service() {

    }

    public Service(String service) {
        this.service = service;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
