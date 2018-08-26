package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("bills/unpaid/")
    public List<Bill> getUnpaidBillsByBankId(HttpServletRequest request) {
        return billService.getUnpaidBillsByBankId(request);
    }

    @PutMapping("bills/pay/{bills}")
    public void payBills(@PathVariable("bills") List<String> bills, HttpServletRequest request) {
        billService.payBills(bills, request);
    }

    @GetMapping("subscribers/{subscriberId}")
    public Subscriber getSubscriberById(@PathVariable("subscriberId") String subscriberId, HttpServletRequest request) {
        return subscriberService.getSubscriberById(subscriberId, request);
    }

    //front-end probably for validation
    @GetMapping("bills/paymentHistoryDescending/{subscriberId}")
    public List<Bill> getSubscriberPaymentsHistoryDescendingByBankId(
            HttpServletRequest request, @PathVariable("subscriberId") String subscriberId) {
        return billService.getSubscriberPaymentsHistoryDescendingByBankId(request, subscriberId);
    }

    @GetMapping("bills/{phoneNumber}/{timeInterval}")
    public List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(
            @PathVariable("timeInterval") List<String> timeInterval,
            @PathVariable("phoneNumber") String phoneNumber,
            HttpServletRequest request) {

        return billService.getMaxAndAvgPaymentInTimeIntervalByBankId(timeInterval, phoneNumber, request);
    }

    //TODO:change this to return what a subscriber has paid for
    @GetMapping("bills/paidservices/")
    public List<Service> getPaidServicesByBankId(HttpServletRequest request) {
        return billService.getPaidServicesByBankId(request);
    }

    @GetMapping("bills/toppayers/")
    public List<BillDTO> getTenBiggestPaymentsByBankId(HttpServletRequest request) {
        return billService.getTenBiggestPaymentsByBankId(request);
    }

    @GetMapping("bills/recentpayments/")
    public List<Bill> getTenMostRecentPaymentsByBankId(HttpServletRequest request) {
        return billService.getTenMostRecentPaymentsByBankId(request);
    }
}
