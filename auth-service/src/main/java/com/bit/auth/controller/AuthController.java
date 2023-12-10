package com.bit.auth.controller;

import com.bit.auth.dto.LoginRequest;
import com.bit.auth.service.JwtService;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Data
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @PostMapping("/login")
  public String
  authenticateAndGetToken(@RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                loginRequest.getPassword()));
    if (authentication.isAuthenticated()) {
      return jwtService.generateToken(loginRequest.getUsername());
    } else {
      throw new UsernameNotFoundException("Invalid user request");
    }
  }

  @GetMapping("/validate")
  public String validateToken(@RequestParam("token") String token) {
    jwtService.validateToken(token);
    return "Token is valid";
  }
}