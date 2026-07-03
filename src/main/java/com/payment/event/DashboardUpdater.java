package com.payment.event;

import com.payment.model.PaymentEvent;

public class DashboardUpdater implements PaymentEventListener {

    @Override
    public void onEvent(PaymentEvent event) {
        System.out.println("    [DASHBOARD] Refreshing for " + event.transactionId());
    }
}
