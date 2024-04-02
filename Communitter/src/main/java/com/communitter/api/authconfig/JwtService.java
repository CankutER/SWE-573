package com.communitter.api.authconfig;

import com.communitter.api.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET="d2bedb814817fa4824c95665b4615260aa71e80b0051b389c229630e4bb57636";
    public String extractEmail(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public String generateToken(User userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String,Object> moreClaims, User userDetails){
        return Jwts.builder().setClaims(moreClaims).setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 86400000))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    private Claims extractAllClaims (String token){
        return Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimExtractor){
        final Claims claims=extractAllClaims(token);
        return claimExtractor.apply(claims);
    }
    public boolean isTokenValid(String token, User userDetails){
        String emailInToken = extractEmail(token);
        return (emailInToken.equals(userDetails.getUsername()) && !isExpired(token));

    }
    public boolean isExpired(String token){
        return extractClaim(token,Claims::getExpiration).before(new Date(System.currentTimeMillis()));
    }
}
