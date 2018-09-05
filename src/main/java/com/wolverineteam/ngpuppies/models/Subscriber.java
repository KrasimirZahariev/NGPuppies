package com.wolverineteam.ngpuppies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @NotNull
    @Digits(integer = 20,fraction = 0, message = "Thephone number can contain only digits!")
    @UniqueElements
    @Id
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "[^0-9]*", message = "Name should contains only alphabetic characters!")
    @Column(name = "FirstName")
    private String firstName;

    @NotNull
    @Pattern(regexp = "[^0-9]*", message = "Name should contains only alphabetic characters!")
    @Column(name = "LastName")
    private String lastName;

    @NotNull
    @Digits(integer = 15,fraction = 0, message = "EGN can contatin only digits!")
    @Column(name = "EGN")
    private String egn;

    @ManyToOne
    @JoinColumn(name = "BankID")
    private User bank;

    @JsonIgnore
    @OneToMany(mappedBy = "subscriber")
    private List<Bill> bills;

    public Subscriber() {

    }

    public Subscriber(String phoneNumber, String firstName, String lastName, String egn, User bank) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.egn = egn;
        this.bank = bank;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public User getBank() {
        return bank;
    }

    public void setBank(User bank) {
        this.bank = bank;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}
