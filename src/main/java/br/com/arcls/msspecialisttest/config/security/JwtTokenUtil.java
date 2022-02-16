package br.com.arcls.msspecialisttest.config.security;

import br.com.arcls.msspecialisttest.domain.model.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final Logger logger;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(jwtIssuer)
                .setClaims(getClaims(user))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 42000)) // 42 Seconds
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public Claims getJwtClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature - {}", ex.getMessage());
            throw ex;
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token - {}", ex.getMessage());
            throw ex;
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token - {}", ex.getMessage());
            throw ex;
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token - {}", ex.getMessage());
            throw ex;
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty - {}", ex.getMessage());
            throw ex;
        }
    }

    private Map<String, Object> getClaims(User user) {
        final Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getId().toString());
        result.put("fullName", user.getFullName());
        result.put("authorities", user.getAuthorities());
        return result;
    }
}
