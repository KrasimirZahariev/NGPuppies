package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BillService {

    List<Bill> getUnpaidBillsByBankId(String bankId);

    void createBill(Bill bill);

    void payBills(List<String> bills);

    List<Object[]> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval);

    List<Bill> getSubscriberPaymentsHistoryDescendingByBankId(HttpServletRequest request, String subscriberId);

    List<Service> getPaidServicesByBankId(String bankId);

    List<Object[]> getTenBiggestPaymentsByBankId(String bankId);

    List<Bill> getTenMostRecentPaymentsByBankId(String bankId);
}
