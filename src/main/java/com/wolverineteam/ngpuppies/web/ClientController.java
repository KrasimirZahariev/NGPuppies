package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/client/")
public class ClientController {

    private SubscriberService subscriberService;
    private BillService billService;
    private UserService userService;

    @Autowired
    public ClientController(UserService userService, SubscriberService subscriberService, BillService billService) {
        this.subscriberService = subscriberService;
        this.billService = billService;
        this.userService = userService;
    }

    @GetMapping("bills/unpaid/")
    public List<Bill> getUnpaidBillsByBankId(HttpServletRequest request) {
        return billService.getUnpaidBillsByBankId(request);
    }

    @PutMapping("bills/pay/{bills}")
    public void payBills(@PathVariable("bills") List<String> bills) {
        billService.payBills(bills);
    }

    @GetMapping("subscribers/{id}")
    public Subscriber getSubscriberById(@PathVariable("id") String subscriberId, HttpServletRequest request) {
        return subscriberService.getSubscriberById(subscriberId, request);
    }

    @GetMapping("bills/paymentHistoryDescending/{subscriberId}")
    public List<Bill> getSubscriberPaymentsHistoryDescendingByBankId(HttpServletRequest request, @PathVariable("subscriberId") String subscriberId) {
        return billService.getSubscriberPaymentsHistoryDescendingByBankId(request, subscriberId);
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

    @GetMapping("users/{username}")
    public User getUser(@PathVariable("username") String username) {
        return userService.loadUserByUsername(username);
    }
}
