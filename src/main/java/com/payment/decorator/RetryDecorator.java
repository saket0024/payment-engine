package com.payment.decorator;

import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResult;
import com.payment.processor.PaymentProcessor;

public class RetryDecorator implements PaymentProcessor {

    private final PaymentProcessor wrapped;
    private final int              maxRetries;

    public RetryDecorator(PaymentProcessor wrapped, int maxRetries) {
        this.wrapped    = wrapped;
        this.maxRetries = maxRetries;
    }

    @Override
    public String getType() { return wrapped.getType(); }

    @Override
    public PaymentResult charge(PaymentRequest req) {
        int attempt = 0;
        while (true) {
            try {
                attempt++;
                return wrapped.charge(req);
            } catch (Exception e) {
                if (attempt >= maxRetries) {
                    System.out.println("  [RETRY] All " + maxRetries + " attempts failed");
                    return new PaymentResult("FAILED", "DECLINED", "Max retries exceeded");
                }
                System.out.println("  [RETRY] Attempt " + attempt + " failed, retrying...");
            }
        }
    }
}
