package com.wolverineteam.ngpuppies.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "ServiceID")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "SubscriberID")
    private Subscriber subscriber;

    @NotNull
    @Column(name = "StartDate")
    private Date startDate;

    @NotNull
    @Column(name = "EndDate")
    private Date endDate;

    @NotNull
    @Digits(integer = 20,fraction = 2,message = "Amount of the bill can contain only digits and must be this format 0.00!")
    @Column(name = "Amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "CurrencyID")
    private Currency currency;

    @Column(name = "PaymentDate")
    private Date paymentDate;

    public Bill() {

    }

    public Bill(Service service, Subscriber subscriber, Date startDate, Date endDate,
                double amount, Currency currency, Date paymentDate) {
        this.service = service;
        this.subscriber = subscriber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.currency = currency;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
