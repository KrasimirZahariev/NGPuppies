package com.wolverineteam.ngpuppies.data.dto;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class BillDTO {

    @UniqueElements
    @Digits(integer = 20,fraction = 0,message = "The phone number should contain only digits!")
    private String phoneNumber; //will be mapped to Subscriber

    @NotNull
    private String service;     //will be mapped to Service

    @NotNull
    private String startDate; //parse this

    @NotNull
    private String endDate;   //parse this

    @NotNull
    private String currency; //mapped to currency load currency by name

    @NotNull
    @Digits(integer = 20,fraction = 00,message ="The Amount should contain only digir and must be in this format 0.00!")
    private double amount;  //

    private String paymentDate;     //parse this

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

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
