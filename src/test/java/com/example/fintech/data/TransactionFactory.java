package com.example.fintech.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TransactionFactory {
    public static Map<String, Object> validTx(String userId) {
        Map<String, Object> t = new HashMap<>();
        t.put("userId", userId);
        t.put("amount", 100.50);
        t.put("type", "transfer");
        t.put("recipientId", UUID.randomUUID().toString().substring(0,6));
        return t;
    }
    public static Map<String, Object> invalidTxMissingAmount(String userId) {
        Map<String, Object> t = new HashMap<>();
        t.put("userId", userId);
        t.put("type", "transfer");
        t.put("recipientId", "456");
        return t;
    }
}
