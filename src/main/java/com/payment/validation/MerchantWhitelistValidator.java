package com.payment.validation;

import com.payment.model.PaymentRequest;

import java.util.Set;

public class MerchantWhitelistValidator extends ValidationHandler {

    private static final Set<String> WHITELIST = Set.of("ATT-MERCHANT-001", "ATT-MERCHANT-002");

    @Override
    protected boolean doValidate(PaymentRequest req) {
        boolean ok = WHITELIST.contains(req.getMerchantId());
        System.out.println("  [CHAIN] Merchant whitelist: " + (ok ? "PASS" : "FAIL (" + req.getMerchantId() + ")"));
        return ok;
    }
}
