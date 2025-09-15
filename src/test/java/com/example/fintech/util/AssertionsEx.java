package com.example.fintech.util;

import org.assertj.core.api.Assertions;

public class AssertionsEx {
    public static void assertValidEmail(String email) {
        Assertions.assertThat(email).contains("@").contains(".");
    }
}
