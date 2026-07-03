package com.payment.gateway;

import com.payment.interfaces.Tokenizable;
import com.payment.processor.CreditCardProcessor;
import com.payment.processor.PaymentProcessor;

public class FiservGatewayFactory implements GatewayFactory {

    @Override
    public PaymentProcessor createProcessor() {
        System.out.println("  [FACTORY] Creating Fiserv CC processor");
        return new CreditCardProcessor();
    }

    @Override
    public Tokenizable createTokenizer() {
        System.out.println("  [FACTORY] Creating Fiserv tokenizer");
        return new CreditCardProcessor();
    }

    @Override
    public SettlementHandler createSettlementHandler() {
        System.out.println("  [FACTORY] Creating Fiserv settlement handler");
        return txnId -> System.out.println("  [FISERV-SETTLE] Settled " + txnId + " via Commerce Hub API");
    }
}
