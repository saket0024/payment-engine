package com.payment.processor;

import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResult;

public class CryptoProcessor implements PaymentProcessor {

    @Override
    public String getType() { return "CRYPTO"; }

    @Override
    public PaymentResult charge(PaymentRequest req) {
        System.out.println("  [CRYPTO] Processing blockchain payment $" + req.getAmountCents() / 100.0);
        return new PaymentResult("TXN-BTC-" + req.getIdempotencyKey(), "CONFIRMED", "Crypto payment confirmed");
    }
}
