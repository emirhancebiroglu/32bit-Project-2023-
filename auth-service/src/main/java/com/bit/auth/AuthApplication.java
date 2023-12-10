package com.bit.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.bit.shared", "com.bit.auth"})
@EnableJpaRepositories("com.bit.shared.repository")
@EntityScan("com.bit.shared.entity")
@EnableDiscoveryClient
public class AuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
  }
}
