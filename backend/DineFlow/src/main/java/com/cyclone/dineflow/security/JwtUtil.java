package com.cyclone.dineflow.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 21-04-2026
 */
@Component
public class JwtUtil {

    private final String SECRET_KEY = "nvjfenvjnjv53352434rnnc19dnwqdneciu439jn";

    private final long ACCESS_EXPIRATION_TIME_IN_M = 15; // Default to 15 minutes

    private final long REFRESH_EXPIRATION_TIME_IN_D = 7; // Default to 7 days

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String buildToken(String userId, List<String> roles, TokenType tokenType){
        String typeString = switch (tokenType){
            case ACCESS -> "Access";
            case REFRESH -> "Refresh";
        };

        Long expirationTimeInMilliSeconds = switch(tokenType){
            case ACCESS -> ACCESS_EXPIRATION_TIME_IN_M *60 * 1000L;
            case REFRESH -> REFRESH_EXPIRATION_TIME_IN_D * 24 * 60 * 60 * 1000L;
        };

        return Jwts.builder()
                .setSubject(userId)
                .claim("userId", userId)
                .claim("role", roles)
                .claim("type", typeString)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMilliSeconds))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String buildAccessToken(String userId, List<String> roles, TokenType tokenType){
        return buildToken(userId,roles,TokenType.ACCESS);
    }

    public String buildRefreshToken(String userId, List<String> roles, TokenType tokenType){
        return buildToken(userId,roles,TokenType.REFRESH);
    }
}
