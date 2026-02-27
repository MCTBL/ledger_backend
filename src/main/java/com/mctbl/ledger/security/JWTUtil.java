package com.mctbl.ledger.security;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	private static final Log logger = LogFactory.getLog(JWTUtil.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.expiration}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        return Jwts.builder()
        		.subject(authentication.getName())
        		.issuedAt(new Date())
        		.expiration(new Date(new Date().getTime() + jwtExpirationMs))
        		.signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
        		.compact();
    }

    public String getUserNameFromJwtToken(String token) {
    	return Jwts.parser()
    			.verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
    			.build()
    			.parseSignedClaims(token)
    			.getPayload()
    			.getSubject();
    }

    public void validateJwtToken(String token) {
        try {
            Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
            .build()
            .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
        	logger.error(e);
        	throw e;
        }
    }

}