package com.payment.model;

public record PaymentEvent(String type, String transactionId, long amountCents, long timestamp) {}
