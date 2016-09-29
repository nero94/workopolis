package com.example.service.impl;

import com.example.domain.Currency;
import com.example.domain.Payment;
import com.example.domain.PaymentState;
import com.example.service.PaymentManagementService;

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
public class PaymentManagementServiceImpl implements PaymentManagementService {
	private static final List<Payment> payments = new ArrayList<>();

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
		XMLGregorianCalendar date = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(c);
		payment.setPaymentDate(date);
		payment.setPaymentState(PaymentState.NEW);

		payments.add(payment);
		
		Payment payment1 = new Payment();
		payment1.setId("100");
		payment1.setAmount(100000);
		payment1.setCurrency(Currency.GBR);
		payment1.setComment("For Fun");
		payment1.setIsRegular(false);
		c.setTime(new Date());
		XMLGregorianCalendar date1 = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(c);
		payment1.setPaymentDate(date1);
		payment1.setPaymentState(PaymentState.NEW);

		payments.add(payment1);

	}

	@Override
	public List<Payment> findAll() {
		return payments;
	}

	@Override
	public Payment findOneById(final String Id) {
		Assert.notNull(Id);

		return payments
				.stream()
				.filter(payment -> payment.getId().equals(Id))
				.findFirst()
				.orElseThrow(
						() -> new RuntimeException(String.format(
								"Payment with id=%s was not found", Id)));
	}

	@Override
	public Payment changePayment(String Id, Payment payment) {
		Payment p = findOneById(Id);

		p.setAmount(payment.getAmount());

		return p;
	}
	
	@Override
	public Payment editPayment(String Id, double amount) {
		Payment p = findOneById(Id);

		p.setAmount(amount);

		return p;
	}
}
