package com.payment.settlement;

import java.util.Map;

public class CyberSourceSettlement extends AbstractSettlementProcessor {

    @Override
    protected void validate(String txnId) {
        System.out.println("  [CYBERSOURCE] Validating via SOAP service...");
    }

    @Override
    protected Map<String, String> prepare(String txnId) {
        System.out.println("  [CYBERSOURCE] Preparing SOAP envelope...");
        return Map.of("format", "XML", "endpoint", "/TransactionProcessor");
    }

    @Override
    protected void execute(String txnId, Map<String, String> data) {
        System.out.println("  [CYBERSOURCE] SOAP call to " + data.get("endpoint") + " for " + txnId);
    }
}
