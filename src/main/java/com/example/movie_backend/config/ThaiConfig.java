package com.example.movie_backend.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties("thai")
@Getter
@Setter
@Component
public class ThaiConfig {
    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private String age;

    @JsonProperty("levels")
    private List<String> levels;

    @JsonProperty("clazz")
    private Clazz clazz;

    @Data
    static public class Clazz {
        @JsonProperty("name")
        private String name;

        @JsonProperty("ten-giao-vien")
        private String tenGiaoVien;
    }


}
