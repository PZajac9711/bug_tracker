package org.bugtracker.bugtracker.model.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtRead {
    public String getLogin(String token){
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(JwtConfig.getSecretLogin())
                .parseClaimsJws(token.substring(7))
                .getBody();
        return claims.get("login").toString();
    }
    public String getEmailFromResetToken(String token){
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(JwtConfig.getSecretResetPassword())
                .parseClaimsJws(token)
                .getBody();
        return claims.get("email").toString();
    }
}
