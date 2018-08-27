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
    public Subscriber getSubscriberById(@PathVariable("subscriberId") String subscriberId,
                                        HttpServletRequest request) {
        return subscriberService.getSubscriberById(subscriberId, request);
    }

    //front-end probably for validation
    @GetMapping("bills/paymentsdescending/")
    public List<Bill> getSubscribersPaymentsHistoryDescendingByBankId(HttpServletRequest request) {
        return billService.getSubscribersPaymentsHistoryDescendingByBankId(request);
    }

    @GetMapping("bills/{subscriberId}/{timeInterval}")
    public List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(
            @PathVariable("timeInterval") List<String> timeInterval,
            @PathVariable("subscriberId") String subscriberId,
            HttpServletRequest request) {

        return billService.getMaxAndAvgPaymentInTimeIntervalByBankId(timeInterval, subscriberId, request);
    }

    @GetMapping("bills/paidservices/{subscriberId}")
    public List<Service> getSubscriberPaidServicesByBankId(@PathVariable("subscriberId") String subscriberId,
                                                           HttpServletRequest request) {
        return billService.getSubscriberPaidServicesByBankId(subscriberId, request);
    }

    @GetMapping("bills/toppayers/")
    public List<BillDTO> getTenBiggestPaymentsByBankId(HttpServletRequest request) {
        return billService.getTenBiggestPaymentsByBankId(request);
    }

    @GetMapping("bills/recentpayments/")
    public List<BillDTO> getTenMostRecentPaymentsByBankId(HttpServletRequest request) {
        return billService.getTenMostRecentPaymentsByBankId(request);
    }
}
