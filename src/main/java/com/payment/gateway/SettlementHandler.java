package com.payment.gateway;

public interface SettlementHandler {
    void settle(String transactionId);
}
