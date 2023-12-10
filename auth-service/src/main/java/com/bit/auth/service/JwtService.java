package com.bit.auth.service;

public interface JwtService {
  String generateToken(String userName);

  void validateToken(final String token);
}
