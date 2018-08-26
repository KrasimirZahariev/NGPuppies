package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BillService {

    List<Bill> getUnpaidBillsByBankId(HttpServletRequest request);

    void createBill(Bill bill);

    void payBills(List<String> bills, HttpServletRequest request);

    List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval, String phoneNumber,
                                                            HttpServletRequest request);

    List<Bill> getSubscriberPaymentsHistoryDescendingByBankId(HttpServletRequest request, String subscriberId);

    List<Service> getPaidServicesByBankId(HttpServletRequest request);

    List<BillDTO> getTenBiggestPaymentsByBankId(HttpServletRequest request);

    List<BillDTO> getTenMostRecentPaymentsByBankId(HttpServletRequest request);
}
