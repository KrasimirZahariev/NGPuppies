package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.data.dto.SubscriberDTO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import com.wolverineteam.ngpuppies.utils.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/client/")
public class ClientController {

    private SubscriberService subscriberService;
    private BillService billService;
    private JwtParser jwtParser;

    @Autowired
    public ClientController(SubscriberService subscriberService, BillService billService, JwtParser jwtParser) {
        this.subscriberService = subscriberService;
        this.billService = billService;
        this.jwtParser = jwtParser;
    }

    @GetMapping("bills/unpaid/")
    public List<Bill> getUnpaidBillsByBankId(HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getUnpaidBillsByBankId(bankId);
    }

    @PutMapping("bills/pay/{bills}")
    public void payBills(@PathVariable("bills") List<String> bills, HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        billService.payBills(bills, bankId);
    }

    @GetMapping("subscribers/{subscriberId}")
    public SubscriberDTO getSubscriberById(@PathVariable("subscriberId") String subscriberId,
                                           HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return subscriberService.getSubscriberById(subscriberId, bankId);
    }

    //front-end probably for validation
    @GetMapping("bills/paymentsdescending/")
    public List<Bill> getSubscribersPaymentsHistoryDescendingByBankId(HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getSubscribersPaymentsHistoryDescendingByBankId(bankId);
    }

    @GetMapping("bills/{subscriberId}/{timeInterval}")
    public List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(
            @PathVariable("timeInterval") List<String> timeInterval,
            @PathVariable("subscriberId") String subscriberId,
            HttpServletRequest request) {

        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getMaxAndAvgPaymentInTimeIntervalByBankId(timeInterval, subscriberId, bankId);
    }

    @GetMapping("bills/paidservices/{subscriberId}")
    public List<Service> getSubscriberPaidServicesByBankId(@PathVariable("subscriberId") String subscriberId,
                                                           HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getSubscriberPaidServicesByBankId(subscriberId, bankId);
    }

    @GetMapping("bills/toppayers/")
    public List<BillDTO> getTenBiggestPaymentsByBankId(HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getTenBiggestPaymentsByBankId(bankId);
    }

    @GetMapping("bills/recentpayments/")
    public List<BillDTO> getTenMostRecentPaymentsByBankId(HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getTenMostRecentPaymentsByBankId(bankId);
    }
}
