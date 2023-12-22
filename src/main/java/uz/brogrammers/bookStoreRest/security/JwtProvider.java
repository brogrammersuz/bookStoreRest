package uz.brogrammers.bookStoreRest.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import uz.brogrammers.bookStoreRest.security.model.UserPrinciple;

import javax.crypto.SecretKey;
import java.util.Date;

import static io.jsonwebtoken.Jwts.SIG.HS512;

@Slf4j
@Component
public class JwtProvider {
    private static final SecretKey key = HS512.key().build();

    public String generateJwtToken(Authentication authentication) {
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("isAdmin", userPrincipal.isAdmin())
                .setIssuedAt(new Date())
                .setExpiration(DateUtils.addHours(new Date(), 2))
                .signWith(key, HS512)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid Jwt token -> Message {} ", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getBody()
                .getSubject();
    }
}
