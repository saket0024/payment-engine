package com.payment.validation;

import com.payment.model.PaymentRequest;

public class CardTokenValidator extends ValidationHandler {

    @Override
    protected boolean doValidate(PaymentRequest req) {
        boolean ok = req.getCardToken() != null && !req.getCardToken().isBlank();
        System.out.println("  [CHAIN] Card token present: " + (ok ? "PASS" : "FAIL"));
        return ok;
    }
}
