package io.github.williamandradesantana.libraryapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.time.Duration;

@Configuration
@EnableWebSecurity
public class AuthorizationServerConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());
        http.oauth2ResourceServer(oauth2rs -> oauth2rs.jwt(Customizer.withDefaults()));
        http.formLogin(configurer -> configurer.loginPage("/login"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public TokenSettings tokenSettings() {
        return TokenSettings.builder()
                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
                .accessTokenTimeToLive(Duration.ofMinutes(60))
                .refreshTokenTimeToLive(Duration.ofMinutes(90))
                .build();
    }

    @Bean
    public ClientSettings clientSettings() {
        return ClientSettings.builder().requireAuthorizationConsent(false).build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                // get token
                .tokenEndpoint("/oauth2/token")
                // verify status token
                .tokenIntrospectionEndpoint("/oauth2/introspect")
                // revogar
                .tokenRevocationEndpoint("/oauth2/revoke")
                .authorizationEndpoint("/oauth2/authorize")
                // user info OPEN ID CONNECT
                .oidcUserInfoEndpoint("/oauth2/userinfo")
                // get public key for verify assign token
                .jwkSetEndpoint("/oauth2/jwks")
                .oidcLogoutEndpoint("/oauth2/logout")
                .build();
    }
}
