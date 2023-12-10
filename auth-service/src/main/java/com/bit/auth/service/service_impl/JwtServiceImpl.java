package com.bit.auth.service.service_impl;

import com.bit.auth.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtServiceImpl implements JwtService {
  @Value("${jwt.secret}") public String SECRET;

  @Override
  public String generateToken(String userName) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userName);
  }

  @Override
  public void validateToken(final String token) {
    Jwts.parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token);
  }

  private String createToken(Map<String, Object> claims, String email) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(email)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
        .signWith(getSignKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
