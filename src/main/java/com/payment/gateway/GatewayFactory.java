package com.payment.gateway;

import com.payment.interfaces.Tokenizable;
import com.payment.processor.PaymentProcessor;

public interface GatewayFactory {
    PaymentProcessor  createProcessor();
    Tokenizable       createTokenizer();
    SettlementHandler createSettlementHandler();
}
