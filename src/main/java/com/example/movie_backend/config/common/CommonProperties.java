package com.example.movie_backend.config.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties("common")
@Component
@Getter
@Setter
public class CommonProperties {
    @JsonProperty("permit-all-path-patterns")
    private List<String> permitAllPathPatterns;

    @JsonProperty("cors")
    private Cors cors;

    @Getter
    @Setter
    public static class Cors {
        private List<String> origins;
    }
}
