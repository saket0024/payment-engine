package com.payment.model;

import java.util.UUID;

public class PaymentRequest {

    private final String accountNo;
    private final long   amountCents;
    private final String currency;
    private final String cardToken;
    private final String merchantId;
    private final String invoiceNo;
    private final String paymentType;
    private final String idempotencyKey;

    private PaymentRequest(Builder b) {
        this.accountNo      = b.accountNo;
        this.amountCents    = b.amountCents;
        this.currency       = b.currency;
        this.cardToken      = b.cardToken;
        this.merchantId     = b.merchantId;
        this.invoiceNo      = b.invoiceNo;
        this.paymentType    = b.paymentType;
        this.idempotencyKey = b.idempotencyKey;
    }

    public String getAccountNo()      { return accountNo; }
    public long   getAmountCents()    { return amountCents; }
    public String getCurrency()       { return currency; }
    public String getCardToken()      { return cardToken; }
    public String getMerchantId()     { return merchantId; }
    public String getInvoiceNo()      { return invoiceNo; }
    public String getPaymentType()    { return paymentType; }
    public String getIdempotencyKey() { return idempotencyKey; }

    @Override
    public String toString() {
        return "PaymentRequest{acct=" + accountNo + ", amt=" + amountCents
                + ", type=" + paymentType + ", key=" + idempotencyKey + "}";
    }

    public static class Builder {
        private String accountNo;
        private long   amountCents;
        private String currency       = "USD";
        private String cardToken;
        private String merchantId;
        private String invoiceNo;
        private String paymentType    = "CREDIT_CARD";
        private String idempotencyKey;

        public Builder accountNo(String v)      { this.accountNo = v;      return this; }
        public Builder amountCents(long v)      { this.amountCents = v;    return this; }
        public Builder currency(String v)       { this.currency = v;       return this; }
        public Builder cardToken(String v)      { this.cardToken = v;      return this; }
        public Builder merchantId(String v)     { this.merchantId = v;     return this; }
        public Builder invoiceNo(String v)      { this.invoiceNo = v;      return this; }
        public Builder paymentType(String v)    { this.paymentType = v;    return this; }
        public Builder idempotencyKey(String v) { this.idempotencyKey = v; return this; }

        public PaymentRequest build() {
            if (accountNo == null || accountNo.isBlank())
                throw new IllegalStateException("accountNo is required");
            if (amountCents <= 0)
                throw new IllegalStateException("amount must be > 0");
            if (idempotencyKey == null)
                this.idempotencyKey = UUID.randomUUID().toString().substring(0, 8);
            return new PaymentRequest(this);
        }
    }
}
