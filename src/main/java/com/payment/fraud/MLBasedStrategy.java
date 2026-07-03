package com.payment.fraud;

import com.payment.model.PaymentRequest;

public class MLBasedStrategy implements FraudStrategy {

    @Override
    public boolean isFraudulent(PaymentRequest req) {
        System.out.println("  [FRAUD] ML model scoring payment...");
        return false;
    }

    @Override
    public String getName() { return "ML_BASED"; }
}
