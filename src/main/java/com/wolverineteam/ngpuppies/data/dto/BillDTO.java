package com.wolverineteam.ngpuppies.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BillDTO {

    private String phoneNumber;

    private String service;

    private String startDate;

    private String endDate;

    private String currency;

    private double amount;

    private String paymentDate;

    public BillDTO() {

    }

    public BillDTO(String phoneNumber, String service, String startDate,
                   String endDate, String currency, double amount) {
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currency = currency;
        this.amount = amount;
        this.paymentDate = null;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
