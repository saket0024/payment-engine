package com.payment.decorator;

import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResult;
import com.payment.processor.PaymentProcessor;

public class LoggingDecorator implements PaymentProcessor {

    private final PaymentProcessor wrapped;

    public LoggingDecorator(PaymentProcessor wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String getType() { return wrapped.getType(); }

    @Override
    public PaymentResult charge(PaymentRequest req) {
        System.out.println("  [LOG] >>> charge() called: " + req);
        long start = System.nanoTime();
        PaymentResult result = wrapped.charge(req);
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.println("  [LOG] <<< charge() returned: " + result.status() + " in " + ms + "ms");
        return result;
    }
}
