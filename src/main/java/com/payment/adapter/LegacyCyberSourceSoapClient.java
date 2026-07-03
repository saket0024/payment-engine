package com.payment.adapter;

public class LegacyCyberSourceSoapClient {

    public String runTransaction(String soapEnvelope) {
        System.out.println("  [LEGACY-SOAP] Processing SOAP envelope...");
        return "<response><status>100</status><txnId>SOAP-9999</txnId></response>";
    }
}
