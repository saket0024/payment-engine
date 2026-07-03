package com.payment.fraud;

import com.payment.model.PaymentRequest;

public interface FraudStrategy {
    boolean isFraudulent(PaymentRequest request);
    String  getName();
}
