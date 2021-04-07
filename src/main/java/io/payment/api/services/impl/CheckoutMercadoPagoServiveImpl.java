package io.payment.api.services.impl;

import io.payment.api.dto.Checkout;
import io.payment.api.dto.CheckoutPayment;
import io.payment.api.dto.Payment;
import io.payment.api.exception.CheckoutException;
import io.payment.api.services.CheckoutSibsService;

public class CheckoutMercadoPagoServiveImpl implements CheckoutSibsService  {

	@Override
	public CheckoutPayment checkout(Checkout checkout) throws CheckoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment payment(String checkoutId, String userId) throws CheckoutException {
		// TODO Auto-generated method stub
		return null;
	}

}
