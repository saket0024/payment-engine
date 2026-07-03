package com.payment.adapter;

import com.payment.model.PaymentRequest;
import com.payment.model.PaymentResult;
import com.payment.processor.PaymentProcessor;

public class LegacyCyberSourceAdapter implements PaymentProcessor {

    private final LegacyCyberSourceSoapClient legacyClient;

    public LegacyCyberSourceAdapter(LegacyCyberSourceSoapClient legacyClient) {
        this.legacyClient = legacyClient;
    }

    @Override
    public String getType() { return "LEGACY_CYBERSOURCE"; }

    @Override
    public PaymentResult charge(PaymentRequest req) {
        String soapEnvelope = "<soap:Envelope><amount>" + req.getAmountCents()
                + "</amount><acct>" + req.getAccountNo() + "</acct></soap:Envelope>";
        legacyClient.runTransaction(soapEnvelope);
        return new PaymentResult("SOAP-9999", "APPROVED", "Legacy CyberSource via adapter");
    }
}
