package org.bugtracker.bugtracker.model.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerate {
    public String generateToken(String userName) {
        long currentTime = System.currentTimeMillis();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JwtConfig.getSecretLogin());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject("Token")
                .claim("login",userName)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + JwtConfig.getExpirationTime()))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
}
