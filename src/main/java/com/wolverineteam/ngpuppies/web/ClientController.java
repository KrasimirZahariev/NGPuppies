package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/")
public class ClientController {

    private SubscriberService subscriberService;
    private BillService billService;

    @Autowired
    public ClientController(SubscriberService subscriberService, BillService billService) {
        this.subscriberService = subscriberService;
        this.billService = billService;
    }

    @GetMapping("bills/unpaid/{id}")
    public List<Bill> getUnpaidBillsByBankId(@PathVariable("id") String bankId) {
        return billService.getUnpaidBillsByBankId(bankId);
    }

    @PutMapping("bills/pay/{bills}")
    public void payBills(@PathVariable("bills") List<String> bills) {
        billService.payBills(bills);
    }

    @GetMapping("subscribers/{id}")
    public Subscriber getSubscriberById(@PathVariable("id") String subscriberId) {
        return subscriberService.getById(subscriberId);
    }

    @GetMapping("bills/paymentHistoryDescending/{bankId}/{subscriberId}")
    public List<Bill> getSubscriberPaymentsHistoryDescendingByBankId(@PathVariable("bankId") String bankId, @PathVariable("subscriberId") String subscriberId) {
        return billService.getSubscriberPaymentsHistoryDescendingByBankId(bankId, subscriberId);
    }

    @GetMapping("bills/{timeInterval}")
    public List<Object[]> getMaxAndAvgPaymentInTimeIntervalByBankId(@PathVariable("timeInterval") List<String> timeInterval) {
        return billService.getMaxAndAvgPaymentInTimeIntervalByBankId(timeInterval);
    }

    @GetMapping("bills/paidServices/{id}")
    public List<Service> getPaidServicesByBankId(@PathVariable("id") String bankId) {
        return billService.getPaidServicesByBankId(bankId);
    }

    @GetMapping("bills/topPayers/{id}")
    public List<Object[]> getTenBiggestPaymentsByBankId(@PathVariable("id") String bankId) {
        return billService.getTenBiggestPaymentsByBankId(bankId);
    }

    @GetMapping("bills/recentPayments/{id}")
    public List<Bill> getTenMostRecentPaymentsByBankId(@PathVariable("id") String bankId) {
        return billService.getTenMostRecentPaymentsByBankId(bankId);
    }
}
