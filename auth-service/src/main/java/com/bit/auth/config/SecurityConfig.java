package com.bit.auth.config;

import com.bit.auth.service.service_impl.CustomUserDetailsServiceImpl;
import com.bit.shared.config.PasswordEncoderConfig;
import com.bit.shared.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  private final PasswordEncoderConfig passwordEncoderConfig;
  private final UserRepository userRepository;

  @Bean
  UserDetailsService userDetailsService() {
    return new CustomUserDetailsServiceImpl(userRepository);
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth
            -> auth.requestMatchers("/auth/login", "/auth/validate")
                   .permitAll()
                   .anyRequest()
                   .authenticated())

        .build();
  }

  @Bean
  AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider =
        new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(
        passwordEncoderConfig.passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  AuthenticationManager
  authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
