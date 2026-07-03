package com.payment.gateway;

import com.payment.interfaces.Tokenizable;
import com.payment.processor.CreditCardProcessor;
import com.payment.processor.PaymentProcessor;

public class CyberSourceGatewayFactory implements GatewayFactory {

    @Override
    public PaymentProcessor createProcessor() {
        System.out.println("  [FACTORY] Creating CyberSource CC processor");
        return new CreditCardProcessor();
    }

    @Override
    public Tokenizable createTokenizer() {
        System.out.println("  [FACTORY] Creating CyberSource tokenizer");
        return new CreditCardProcessor();
    }

    @Override
    public SettlementHandler createSettlementHandler() {
        System.out.println("  [FACTORY] Creating CyberSource settlement handler");
        return txnId -> System.out.println("  [CYBERSOURCE-SETTLE] Settled " + txnId + " via SOAP batch");
    }
}
