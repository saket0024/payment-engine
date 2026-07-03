package com.payment.fraud;

import com.payment.model.PaymentRequest;

public class RuleBasedStrategy implements FraudStrategy {

    @Override
    public boolean isFraudulent(PaymentRequest req) {
        boolean flagged = req.getAmountCents() > 100_000_00;
        System.out.println("  [FRAUD] Rule-based check: amt=$" + req.getAmountCents() / 100.0
                + (flagged ? " → FLAGGED" : " → OK"));
        return flagged;
    }

    @Override
    public String getName() { return "RULE_BASED"; }
}
