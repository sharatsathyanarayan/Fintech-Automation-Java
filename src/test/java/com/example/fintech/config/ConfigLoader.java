package com.example.fintech.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigLoader {
    private static final TestConfig CFG = ConfigFactory.create(TestConfig.class, System.getProperties());
    public static TestConfig cfg() { return CFG; }
}
