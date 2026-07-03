package com.payment.event;

import com.payment.model.PaymentEvent;

public class LedgerRecorder implements PaymentEventListener {

    @Override
    public void onEvent(PaymentEvent event) {
        System.out.println("    [LEDGER] Recording $" + event.amountCents() / 100.0
                + " for " + event.transactionId());
    }
}
