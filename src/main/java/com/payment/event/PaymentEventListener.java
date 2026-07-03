package com.payment.event;

import com.payment.model.PaymentEvent;

public interface PaymentEventListener {
    void onEvent(PaymentEvent event);
}
