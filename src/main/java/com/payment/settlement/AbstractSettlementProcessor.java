package com.payment.settlement;

import java.util.Map;

public abstract class AbstractSettlementProcessor {

    public final void settle(String transactionId) {
        System.out.println("  [SETTLE] === Settlement for " + transactionId + " ===");
        validate(transactionId);
        Map<String, String> data = prepare(transactionId);
        execute(transactionId, data);
        confirm(transactionId);
    }

    protected abstract void validate(String transactionId);
    protected abstract Map<String, String> prepare(String transactionId);
    protected abstract void execute(String transactionId, Map<String, String> data);

    protected void confirm(String transactionId) {
        System.out.println("  [SETTLE] ✓ Settlement confirmed: " + transactionId);
    }
}
