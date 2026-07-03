package com.payment.fraud;

import com.payment.model.PaymentRequest;

public class FraudEngine {

    private FraudStrategy strategy;

    public void setStrategy(FraudStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean check(PaymentRequest request) {
        System.out.println("  [FRAUD-ENGINE] Running " + strategy.getName() + " strategy");
        return strategy.isFraudulent(request);
    }
}
