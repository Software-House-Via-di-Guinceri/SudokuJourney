package com.shvg.sudokujourney.util;

import com.google.api.client.util.Value;
import com.shvg.sudokujourney.model.dto.UserDto;
import com.shvg.sudokujourney.service.database.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserService userService;

    @Value("${jwt.secret.key}")
    private String secret;
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public String generateToken(String userId) {
        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public UserDto validateToken(String token) throws JwtException {
        UserDto user = userService.getById(extractUserId(token));
        if(Objects.isNull(user) || !(extractUserId(token).equals(user.getId()) && !isTokenExpired(token))) throw new JwtException("Token not valid");
        return user;
    }


    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
