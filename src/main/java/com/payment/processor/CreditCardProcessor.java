package com.payment.processor;

import com.payment.interfaces.Refundable;
import com.payment.interfaces.Tokenizable;
import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResult;

public class CreditCardProcessor implements PaymentProcessor, Refundable, Tokenizable {

    @Override
    public String getType() { return "CREDIT_CARD"; }

    @Override
    public PaymentResult charge(PaymentRequest req) {
        System.out.println("  [CC] Charging $" + req.getAmountCents() / 100.0
                + " via Fiserv for acct " + req.getAccountNo());
        return new PaymentResult("TXN-CC-" + req.getIdempotencyKey(), "APPROVED", "Credit card charged");
    }

    @Override
    public PaymentResult refund(String txnId, long amountCents) {
        System.out.println("  [CC] Refunding $" + amountCents / 100.0 + " for " + txnId);
        return new PaymentResult("REF-" + txnId, "REFUNDED", "Refund processed");
    }

    @Override
    public String tokenize(String cardNumber) {
        String token = "TOK-" + cardNumber.substring(cardNumber.length() - 4);
        System.out.println("  [CC] Tokenized card → " + token);
        return token;
    }
}
