package com.payment.validation;

import com.payment.model.PaymentRequest;

public abstract class ValidationHandler {

    private ValidationHandler next;

    public ValidationHandler setNext(ValidationHandler next) {
        this.next = next;
        return next;
    }

    public final boolean validate(PaymentRequest request) {
        if (!doValidate(request)) {
            return false;
        }
        if (next != null) {
            return next.validate(request);
        }
        return true;
    }

    protected abstract boolean doValidate(PaymentRequest request);
}
