package io.payment.api.services;

import org.springframework.stereotype.Service;

import io.payment.api.dto.Checkout;
import io.payment.api.dto.CheckoutPayment;
import io.payment.api.dto.Payment;
import io.payment.api.exception.CheckoutException;

@Service
public interface CheckoutSibsService {

	public CheckoutPayment checkout(Checkout checkout) throws CheckoutException;

	public Payment payment(String checkoutId, String userId) throws CheckoutException;

}
