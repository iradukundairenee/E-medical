package com.example.ehospital.helpers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;



public class JwtTokenProvider {
    private static final String SECRET = "my-secret";

    public String generateToken(String string) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(string)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS512, SECRET);
                 
        return builder.compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);

            return claims.getBody().getSubject() != null;
        } catch (JwtException e) {
            return false;
        }
    }

    
}
