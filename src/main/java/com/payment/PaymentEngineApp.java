package com.payment;

import com.payment.adapter.LegacyCyberSourceAdapter;
import com.payment.adapter.LegacyCyberSourceSoapClient;
import com.payment.config.PaymentConfig;
import com.payment.decorator.LoggingDecorator;
import com.payment.decorator.RetryDecorator;
import com.payment.event.DashboardUpdater;
import com.payment.event.EmailNotifier;
import com.payment.event.LedgerRecorder;
import com.payment.event.PaymentEventPublisher;
import com.payment.facade.PaymentFacade;
import com.payment.fraud.FraudEngine;
import com.payment.fraud.RuleBasedStrategy;
import com.payment.fraud.VelocityCheckStrategy;
import com.payment.gateway.CyberSourceGatewayFactory;
import com.payment.gateway.FiservGatewayFactory;
import com.payment.gateway.GatewayFactory;
import com.payment.model.PaymentEvent;
import com.payment.model.PaymentRequest;
import com.payment.processor.CreditCardProcessor;
import com.payment.processor.PaymentProcessor;
import com.payment.processor.PaymentProcessorFactory;
import com.payment.proxy.TokenizationCacheProxy;
import com.payment.settlement.AbstractSettlementProcessor;
import com.payment.settlement.CyberSourceSettlement;
import com.payment.settlement.FiservSettlement;
import com.payment.validation.AmountLimitValidator;
import com.payment.validation.CardTokenValidator;
import com.payment.validation.IdempotencyValidator;
import com.payment.validation.MerchantWhitelistValidator;

public class PaymentEngineApp {

    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║  PAYMENT ENGINE — SOLID + 12 Design Patterns Demo   ║");
        System.out.println("╚═════════════════════════════════════════════════════╝\n");

        System.out.println("═══ 1. SINGLETON: PaymentConfig ═══");
        PaymentConfig config = PaymentConfig.getInstance();
        System.out.println("Config: " + config);
        System.out.println("Same instance? " + (config == PaymentConfig.getInstance()) + "\n");

        System.out.println("═══ 2. BUILDER: PaymentRequest ═══");
        PaymentRequest ccRequest = new PaymentRequest.Builder()
                .accountNo("ACC-123456")
                .amountCents(15999)
                .currency("USD")
                .cardToken("4111111111111111")
                .merchantId("ATT-MERCHANT-001")
                .invoiceNo("INV-2024-0042")
                .paymentType("CREDIT_CARD")
                .build();
        System.out.println("Built: " + ccRequest + "\n");

        System.out.println("═══ 3. FACTORY METHOD: PaymentProcessorFactory ═══");
        PaymentProcessor ccProc  = PaymentProcessorFactory.create("CREDIT_CARD");
        PaymentProcessor achProc = PaymentProcessorFactory.create("ACH");
        System.out.println("CC type: "  + ccProc.getType());
        System.out.println("ACH type: " + achProc.getType() + "\n");

        System.out.println("═══ 4. ABSTRACT FACTORY: GatewayFactory ═══");
        GatewayFactory fiservFactory = new FiservGatewayFactory();
        fiservFactory.createProcessor();
        fiservFactory.createTokenizer();
        fiservFactory.createSettlementHandler();
        System.out.println();
        
        System.out.println(" ********************[Saket's Implementaion]*********************");
        
        GatewayFactory cyberSourceFactory = new CyberSourceGatewayFactory();
        cyberSourceFactory.createProcessor();
        cyberSourceFactory.createSettlementHandler();
        cyberSourceFactory.createTokenizer();
        System.out.println();
        
        System.out.println("═══ 5. ADAPTER: LegacyCyberSourceAdapter ═══");
        LegacyCyberSourceSoapClient oldClient = new LegacyCyberSourceSoapClient();
        PaymentProcessor adapted = new LegacyCyberSourceAdapter(oldClient);
        adapted.charge(ccRequest);
        System.out.println();

        System.out.println("═══ 6. FACADE: PaymentFacade ═══");
        new PaymentFacade().processPayment(ccRequest);
        System.out.println();

        System.out.println("═══ 7. DECORATOR: Logging + Retry ═══");
        PaymentProcessor decorated = new RetryDecorator(
                new LoggingDecorator(new CreditCardProcessor()), 3);
        decorated.charge(ccRequest);
        System.out.println();

        System.out.println("═══ 8. PROXY: TokenizationCacheProxy ═══");
        TokenizationCacheProxy proxy = new TokenizationCacheProxy(new CreditCardProcessor());
        proxy.tokenize("4111111111111111");
        proxy.tokenize("4111111111111111");
        proxy.tokenize("5500000000000004");
        System.out.println("Cache size: " + proxy.cacheSize() + "\n");

        System.out.println("═══ 9. STRATEGY: FraudDetectionStrategy ═══");
        FraudEngine fraudEngine = new FraudEngine();
        fraudEngine.setStrategy(new VelocityCheckStrategy());
        fraudEngine.check(ccRequest);
        fraudEngine.setStrategy(new RuleBasedStrategy());
        fraudEngine.check(ccRequest);
        System.out.println();

        System.out.println("═══ 10. OBSERVER: PaymentEventPublisher ═══");
        PaymentEventPublisher publisher = new PaymentEventPublisher();
        publisher.subscribe(new EmailNotifier());
        publisher.subscribe(new LedgerRecorder());
        publisher.subscribe(new DashboardUpdater());
        publisher.publish(new PaymentEvent("PAYMENT_APPROVED", "TXN-CC-001", 15999, System.currentTimeMillis()));
        System.out.println();

        System.out.println("═══ 11. TEMPLATE METHOD: Settlement ═══");
        AbstractSettlementProcessor fiservSettlement = new FiservSettlement();
        fiservSettlement.settle("TXN-CC-001");
        System.out.println();
        AbstractSettlementProcessor cyberSettlement = new CyberSourceSettlement();
        cyberSettlement.settle("TXN-SOAP-9999");
        System.out.println();

        System.out.println("═══ 12. CHAIN OF RESPONSIBILITY: Validation ═══");
        AmountLimitValidator chain = new AmountLimitValidator();
        chain.setNext(new CardTokenValidator())
             .setNext(new MerchantWhitelistValidator())
             .setNext(new IdempotencyValidator());

        System.out.println("--- Valid request ---");
        boolean valid = chain.validate(ccRequest);
        System.out.println("Result: " + (valid ? "ALL PASSED ✓" : "REJECTED ✗"));

        System.out.println("\n--- Invalid request (no card token) ---");
        PaymentRequest badReq = new PaymentRequest.Builder()
                .accountNo("ACC-999")
                .amountCents(5000)
                .merchantId("ATT-MERCHANT-001")
                .build();
        boolean valid2 = chain.validate(badReq);
        System.out.println("Result: " + (valid2 ? "ALL PASSED ✓" : "REJECTED ✗"));

        System.out.println("\n═══ DONE — All 12 patterns demonstrated ═══");
    }
}
