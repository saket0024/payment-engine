package com.payment.validation;

import com.payment.model.PaymentRequest;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class IdempotencyValidator extends ValidationHandler {

    private final Set<String> processed = ConcurrentHashMap.newKeySet();

    @Override
    protected boolean doValidate(PaymentRequest req) {
        boolean duplicate = !processed.add(req.getIdempotencyKey());
        System.out.println("  [CHAIN] Idempotency: " + (duplicate ? "FAIL (duplicate)" : "PASS"));
        return !duplicate;
    }
}
