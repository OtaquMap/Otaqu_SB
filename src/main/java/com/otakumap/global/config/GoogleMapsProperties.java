package com.otakumap.global.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "google")
public class GoogleMapsProperties {
    private GoogleMapsProperties.ProviderProperties api;

    @Data
    public static class ProviderProperties {
        private String key;
    }
}
