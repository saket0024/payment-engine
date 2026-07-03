package com.payment.interfaces;

import com.payment.model.PaymentResult;

public interface Refundable {
    PaymentResult refund(String transactionId, long amountCents);
}
