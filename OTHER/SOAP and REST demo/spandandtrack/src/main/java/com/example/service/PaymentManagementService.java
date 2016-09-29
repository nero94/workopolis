package com.example.service;

import com.example.domain.Payment;

import java.util.List;

/**
 * @author yuriy.dizhak
 */
public interface PaymentManagementService {

    List<Payment> findAll();

    Payment findOneById(String Id);

    Payment changePayment(String Id, Payment payment);

	Payment editPayment(String id, double amount);

}
