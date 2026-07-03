package com.payment.settlement;

import java.util.Map;

public class FiservSettlement extends AbstractSettlementProcessor {

    @Override
    protected void validate(String txnId) {
        System.out.println("  [FISERV] Validating via Commerce Hub API...");
    }

    @Override
    protected Map<String, String> prepare(String txnId) {
        System.out.println("  [FISERV] Preparing REST payload...");
        return Map.of("format", "JSON", "endpoint", "/v1/settlements");
    }

    @Override
    protected void execute(String txnId, Map<String, String> data) {
        System.out.println("  [FISERV] POST " + data.get("endpoint") + " for " + txnId);
    }
}
