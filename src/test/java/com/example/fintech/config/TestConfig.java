package com.example.fintech.config;

import org.aeonbits.owner.Config;

@Config.Sources({
    "classpath:env/${env}.properties",
    "system:properties"
})
public interface TestConfig extends Config {
    @Key("env.name") @DefaultValue("dev") String envName();
    @Key("api.baseUrl") @DefaultValue("http://localhost:8089") String apiBaseUrl();
    @Key("ui.basePath") @DefaultValue("src/test/resources/ui/mock/index.html") String uiBasePath();
    @Key("auth.token") @DefaultValue("Bearer demo-token") String authToken();
    @Key("browser") @DefaultValue("chrome") String browser();
}
