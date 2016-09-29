package com.example.web;

import com.example.domain.Payment;
import com.example.service.PaymentManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuriy.dizhak
 */
@RestController
@RequestMapping(value = "/payments")
public class PaymentManagementController {

    @Autowired
    private PaymentManagementService paymentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Payment> getPayments() {
        return paymentService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Payment findPayment(@PathVariable(value = "id") String id) {
        return paymentService.findOneById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
            //consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Payment changePayment(@PathVariable(value = "id") String id, @RequestBody Payment payment) {
        return paymentService.changePayment(id, payment);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
            //consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Payment editPayment(@PathVariable(value = "id") String id, @RequestBody Payment payment ) {
        return paymentService.editPayment(id, payment.getAmount());
    }

}
