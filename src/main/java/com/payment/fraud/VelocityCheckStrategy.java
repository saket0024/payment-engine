package com.payment.fraud;

import com.payment.model.PaymentRequest;

public class VelocityCheckStrategy implements FraudStrategy {

    @Override
    public boolean isFraudulent(PaymentRequest req) {
        System.out.println("  [FRAUD] Velocity check on acct " + req.getAccountNo());
        return false;
    }

    @Override
    public String getName() { return "VELOCITY"; }
}
