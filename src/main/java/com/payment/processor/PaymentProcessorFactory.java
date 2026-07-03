package com.payment.processor;

public class PaymentProcessorFactory {

    public static PaymentProcessor create(String paymentType) {
        return switch (paymentType) {
            case "CREDIT_CARD" -> new CreditCardProcessor();
            case "ACH"         -> new ACHProcessor();
            case "CRYPTO"      -> new CryptoProcessor();
            default -> throw new IllegalArgumentException("Unknown payment type: " + paymentType);
        };
    }
}
