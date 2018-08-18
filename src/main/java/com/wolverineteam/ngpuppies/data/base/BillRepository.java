package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.Bill;
import java.util.List;


public interface BillRepository {

    Bill getById(int id);

    void createBill(Bill bill);

    //They must have access to bill payment module where they can pay a particular bill
    // (or selected list of bills) for their subscribers
    List<Bill> getUnpaidBillsByBankId(int bankId);
    void pay(List<Integer> bills);



    //A client should be able to see a history of the payments for its subscribers sorted
    // descending by the date of payment
    List<Bill> getAllPaidSorted(int bankId);


    //A client should be able to see the average and max amount of money
    // payed for a subscriber for a defined period of time


    //A client should be able to see a list of the services the client has paid for
    List<Bill> getAllPaidServices(int bankId);
    //Top 10 subscribers with the biggest amount of money payed.
    // If there are payments with amounts in different currencies,
    // they should be all converted to BGN (using a static conversion rate) and summed


    //The 10 most recent payments for the particular client for all the subscribers
    List<Bill> GetTenMostRecentPaymentsByBankId(int bankId);
}
