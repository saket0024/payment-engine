package com.payment.facade;

import com.payment.interfaces.Tokenizable;
import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResult;
import com.payment.processor.PaymentProcessor;
import com.payment.processor.PaymentProcessorFactory;

public class PaymentFacade {

    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("  [FACADE] Starting payment orchestration...");

        PaymentProcessor processor = PaymentProcessorFactory.create(request.getPaymentType());

        if (processor instanceof Tokenizable t) {
            t.tokenize(request.getCardToken());
        }

        PaymentResult result = processor.charge(request);

        System.out.println("  [FACADE] Settlement queued for " + result.transactionId());
        System.out.println("  [FACADE] Payment complete: " + result.status());
        return result;
    }
}
