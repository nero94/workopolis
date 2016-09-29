package com.example.repository;

import com.example.domain.*;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author yuriy.dizhak
 */
@Component
public class SpendAndTrackRepository {
    private static final List<Payment> payments = new ArrayList<Payment>();

    @PostConstruct
    public void initData() throws DatatypeConfigurationException {
        Payment payment = new Payment();
        payment.setId("123");
        payment.setAmount(123);
        payment.setCurrency(Currency.USD);
        payment.setComment("Food Payment");
        payment.setIsRegular(false);
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        payment.setPaymentDate(date);
        payment.setPaymentState(PaymentState.NEW);
                      
        payments.add(payment);

    }

    public Payment findPayment(final String id) {
        Assert.notNull(id);

        return payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Payment with id=%s was not found", id)));
    }
}
