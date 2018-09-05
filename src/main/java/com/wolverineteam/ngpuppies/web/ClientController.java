package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.data.dao.BillDAO;
import com.wolverineteam.ngpuppies.data.dao.SubscriberDAO;
import com.wolverineteam.ngpuppies.exception.ForbiddenSubscriberException;
import com.wolverineteam.ngpuppies.exception.SubscriberNotFountException;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import com.wolverineteam.ngpuppies.utils.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client/")
public class ClientController {

    private SubscriberService subscriberService;
    private BillService billService;
    private UserService userService;
    private JwtParser jwtParser;

    @Autowired
    public ClientController(SubscriberService subscriberService, BillService billService, JwtParser jwtParser, UserService userService) {
        this.subscriberService = subscriberService;
        this.billService = billService;
        this.jwtParser = jwtParser;
        this.userService = userService;
    }

    @GetMapping("bills/unpaid/")
    public List<Bill> getUnpaidBillsByBankId(HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getUnpaidBillsByBankId(bankId);
    }

    @PostMapping("bills/pay/")
    public void payBills(@RequestBody List<String> billsId, HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        billService.payBills(billsId, bankId);
    }

    @GetMapping("subscribers/{subscriberId}")
    public SubscriberDAO getSubscriberById(@PathVariable("subscriberId") String subscriberId,
                                           HttpServletRequest request) throws ForbiddenSubscriberException {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);

        HashSet<String> subs = subscriberService.getAllTelecomsSubscribers().stream()
                .map(SubscriberDAO::getPhoneNumber).collect(Collectors.toCollection(HashSet::new));

        if (!subs.contains(subscriberId)){
            throw new SubscriberNotFountException("This subscriber does not exist!");

        }else
        {
            HashSet<String> bankSubs = subscriberService.getAllSubscribersByBankId(bankId).stream()
                    .map(SubscriberDAO::getPhoneNumber).collect(Collectors.toCollection(HashSet::new));
            if (!bankSubs.contains(subscriberId)){
                throw new ForbiddenSubscriberException("Yod don't have access to these subscribers details!");
            }
        }

        return subscriberService.getSubscriberDAOById(subscriberId, bankId);
    }

    @GetMapping("subscribers/")
    public List<SubscriberDAO> getAllSubscribersByBankId(
                                           HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return subscriberService.getAllSubscribersByBankId(bankId);
    }

    //front-end probably for validation
    @GetMapping("bills/payments-descending/")
    public List<Bill> getSubscribersPaymentsHistoryDescendingByBankId(HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getSubscribersPaymentsHistoryDescendingByBankId(bankId);
    }

    @GetMapping("bills/{subscriberId}/{timeInterval}")
    public List<BillDAO> getMaxAndAvgPaymentInTimeIntervalByBankId(
            @PathVariable("timeInterval") List<String> timeInterval,
            @PathVariable("subscriberId") String subscriberId,
            HttpServletRequest request) {

        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getMaxAndAvgPaymentInTimeIntervalByBankId(timeInterval, subscriberId, bankId);
    }

    @GetMapping("bills/paid-services/{subscriberId}")
    public List<Service> getSubscriberPaidServicesByBankId(@PathVariable("subscriberId") String subscriberId,
                                                           HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getSubscriberPaidServicesByBankId(subscriberId, bankId);
    }

    @GetMapping("bills/top-payments/")
    public List<BillDAO> getTenBiggestPayersByBankId(HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getTenBiggestPayersByBankId(bankId);
    }

    @GetMapping("bills/recent-payments/")
    public List<BillDAO> getTenMostRecentPaymentsByBankId(HttpServletRequest request) {
        int bankId = jwtParser.getBankIdByUsernameFromToken(request);
        return billService.getTenMostRecentPaymentsByBankId(bankId);
    }
}
