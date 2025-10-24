package com.umg.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain security(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(Customizer.withDefaults())
                .authorizeExchange(ex -> ex
                .pathMatchers(HttpMethod.POST, "/auth/register", "/auth/login").permitAll()
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers(HttpMethod.GET,
                        "/api/v1/libros", "/api/v1/libros/**",
                        "/api/v1/authors", "/api/v1/authors/**")
                .hasAnyRole("ESTUDIANTE", "BIBLIOTECARIO", "ADMIN")
                .pathMatchers(HttpMethod.POST,
                        "/api/v1/libros", "/api/v1/libros/**",
                        "/api/v1/authors", "/api/v1/authors/**")
                .hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .pathMatchers(HttpMethod.PUT,
                        "/api/v1/libros", "/api/v1/libros/**",
                        "/api/v1/authors", "/api/v1/authors/**")
                .hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .pathMatchers(HttpMethod.DELETE,
                        "/api/v1/libros", "/api/v1/libros/**",
                        "/api/v1/authors", "/api/v1/authors/**")
                .hasRole("ADMIN")
                .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth.jwt(jwt -> {
            var granted = new JwtGrantedAuthoritiesConverter();
            granted.setAuthoritiesClaimName("roles"); // claim del JWT
            granted.setAuthorityPrefix("ROLE_");      // prefijo que espera hasRole

            var delegate = new JwtAuthenticationConverter();
            delegate.setJwtGrantedAuthoritiesConverter(granted);

            var adapter = new ReactiveJwtAuthenticationConverterAdapter(delegate);
            jwt.jwtAuthenticationConverter(adapter);
        }))
                .build();
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder(
            @Value("${spring.security.oauth2.resourceserver.jwt.secret-key:${JWT_SECRET:}}") String secret) {

        if (secret == null || secret.isBlank() || secret.length() < 32) {
            throw new IllegalStateException("JWT secret debe existir y tener >= 32 caracteres.");
        }
        SecretKey key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        return NimbusReactiveJwtDecoder.withSecretKey(key).build();
    }
}
