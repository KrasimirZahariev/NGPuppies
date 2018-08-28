package com.wolverineteam.ngpuppies.services.base;

import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BillService {

    List<Bill> getUnpaidBillsByBankId(int bankId);

    void createBill(Bill bill);

    void payBills(List<String> bills, int bankId);

    List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval, String subscriberId,
                                                            int bankId);

    List<Bill> getSubscribersPaymentsHistoryDescendingByBankId(int bankId);

    List<Service> getSubscriberPaidServicesByBankId(String subscriberId, int bankId);

    List<BillDTO> getTenBiggestPaymentsByBankId(int bankId);

    List<BillDTO> getTenMostRecentPaymentsByBankId(int bankId);
}
