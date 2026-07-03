package com.payment.processor;

import com.payment.interfaces.Chargeable;

public interface PaymentProcessor extends Chargeable {
    String getType();
}
