package com.payment.interfaces;

import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResult;

public interface Chargeable {
    PaymentResult charge(PaymentRequest request);
}
