package com.payment.event;

import com.payment.model.PaymentEvent;

public class EmailNotifier implements PaymentEventListener {

    @Override
    public void onEvent(PaymentEvent event) {
        System.out.println("    [EMAIL] Sending receipt for " + event.transactionId());
    }
}
