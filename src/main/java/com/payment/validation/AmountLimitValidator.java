package com.payment.validation;

import com.payment.model.PaymentRequest;

public class AmountLimitValidator extends ValidationHandler {

    @Override
    protected boolean doValidate(PaymentRequest req) {
        boolean ok = req.getAmountCents() > 0 && req.getAmountCents() <= 500_000_00;
        System.out.println("  [CHAIN] Amount limit: " + (ok ? "PASS" : "FAIL (" + req.getAmountCents() + ")"));
        return ok;
    }
}
