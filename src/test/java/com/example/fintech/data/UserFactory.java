package com.example.fintech.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserFactory {
    public static Map<String, Object> validUser() {
        Map<String, Object> u = new HashMap<>();
        u.put("name", "John Doe " + UUID.randomUUID().toString().substring(0,8));
        u.put("email", "john+" + UUID.randomUUID().toString().substring(0,6) + "@example.com");
        u.put("accountType", "premium");
        return u;
    }
    public static Map<String, Object> invalidUserMissingEmail() {
        Map<String, Object> u = new HashMap<>();
        u.put("name", "No Email");
        u.put("accountType", "basic");
        return u;
    }
}
