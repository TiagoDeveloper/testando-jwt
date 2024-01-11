package com.tiagodeveloper.springbootwithjwtsecurity.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collection;

@ConfigurationProperties(prefix = "entry-point")
public record SecurityMappingConfig(Collection<Mapping> mapping) {

    @Data
    public static class Mapping{
        private String path;
        private String[] roles;
    }
}
