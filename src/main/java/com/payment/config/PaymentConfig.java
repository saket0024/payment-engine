package com.payment.config;

public class PaymentConfig {

    private final String gatewayUrl;
    private final String merchantId;
    private final int    timeoutMs;
    private final int    maxRetries;

    private PaymentConfig() {
        this.gatewayUrl = "https://api.fiserv.com/commerce-hub/v1";
        this.merchantId = "ATT-MERCHANT-001";
        this.timeoutMs  = 5000;
        this.maxRetries = 3;
    }

    private static class Holder {
        private static final PaymentConfig INSTANCE = new PaymentConfig();
    }

    public static PaymentConfig getInstance() {
        return Holder.INSTANCE;
    }

    public String getGatewayUrl() { return gatewayUrl; }
    public String getMerchantId() { return merchantId; }
    public int    getTimeoutMs()  { return timeoutMs; }
    public int    getMaxRetries() { return maxRetries; }

    @Override
    public String toString() {
        return "PaymentConfig{gateway=" + gatewayUrl + ", merchant=" + merchantId + "}";
    }
}
