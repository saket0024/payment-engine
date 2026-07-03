package com.payment.processor;

import com.payment.interfaces.Refundable;
import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResult;

public class ACHProcessor implements PaymentProcessor, Refundable {

    @Override
    public String getType() { return "ACH"; }

    @Override
    public PaymentResult charge(PaymentRequest req) {
        System.out.println("  [ACH] Initiating bank transfer $" + req.getAmountCents() / 100.0
                + " for acct " + req.getAccountNo());
        return new PaymentResult("TXN-ACH-" + req.getIdempotencyKey(), "PENDING", "ACH initiated (T+2 settlement)");
    }

    @Override
    public PaymentResult refund(String txnId, long amountCents) {
        System.out.println("  [ACH] ACH refund $" + amountCents / 100.0 + " for " + txnId);
        return new PaymentResult("REF-" + txnId, "REFUND_PENDING", "ACH refund initiated");
    }
}
