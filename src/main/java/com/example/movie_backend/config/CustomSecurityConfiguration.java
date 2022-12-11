package com.example.movie_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Objects;

@EnableWebSecurity
@Configuration
public class CustomSecurityConfiguration {
    private final com.example.movie_backend.config.security.CommonProperties commonProperties;

    public CustomSecurityConfiguration(com.example.movie_backend.config.security.CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //
        corsConfig(http);
        //
        permitAll(http);

        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        return http.build();
    }

    /**
     * @param http
     * @throws Exception
     */
    private void permitAll(HttpSecurity http) throws Exception {
        if (!Objects.isNull(commonProperties.getPermitAllPathPatterns())) {
            for (String path : commonProperties.getPermitAllPathPatterns()) {
                http.authorizeHttpRequests().antMatchers(path).permitAll();
            }
        }

        http.authorizeHttpRequests().anyRequest().authenticated();
    }

    /**
     * @param http
     * @throws Exception
     */
    private void corsConfig(HttpSecurity http) throws Exception {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowCredentials(true);
        if (!Objects.isNull(commonProperties.getCors().getOrigins())) {
            configuration.setAllowedOrigins(commonProperties.getCors().getOrigins());
        }

        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        http.cors(cors -> cors.configurationSource(source));
    }
}
