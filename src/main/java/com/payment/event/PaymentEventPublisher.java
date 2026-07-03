package com.payment.event;

import com.payment.model.PaymentEvent;

import java.util.ArrayList;
import java.util.List;

public class PaymentEventPublisher {

    private final List<PaymentEventListener> listeners = new ArrayList<>();

    public void subscribe(PaymentEventListener listener) {
        listeners.add(listener);
    }

    public void publish(PaymentEvent event) {
        System.out.println("  [PUB] Publishing " + event.type() + " for " + event.transactionId());
        for (PaymentEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
