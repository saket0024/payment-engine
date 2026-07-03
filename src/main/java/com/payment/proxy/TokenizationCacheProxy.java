package com.payment.proxy;

import com.payment.interfaces.Tokenizable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenizationCacheProxy implements Tokenizable {

    private final Tokenizable          realTokenizer;
    private final Map<String, String>  cache = new ConcurrentHashMap<>();

    public TokenizationCacheProxy(Tokenizable realTokenizer) {
        this.realTokenizer = realTokenizer;
    }

    @Override
    public String tokenize(String cardNumber) {
        return cache.computeIfAbsent(cardNumber, card -> {
            System.out.println("  [PROXY] Cache MISS — calling real tokenizer");
            return realTokenizer.tokenize(card);
        });
    }

    public int cacheSize() { return cache.size(); }
}
