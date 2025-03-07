package br.edu.ifpi.springsecurityjwt.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${token.jwt.expiration}")
    private long tokenExpirationTime;

    /*@Value("${token.jwt.secret}")*/
    private final String secretKey = generateSecretKey();

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + tokenExpirationTime);

        return Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();

    }

    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;
    }

    public String getUsername(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /*private SecretKey key(){
        return Jwts.SIG.HS256.key().build();
    }*/

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generateSecretKey() {
        // length means (32 bytes are required for 256-bit key)
        int length = 32;

        // Create a secure random generator
        SecureRandom secureRandom = new SecureRandom();

        // Create a byte array to hold the random bytes
        byte[] keyBytes = new byte[length];

        // Generate the random bytes
        secureRandom.nextBytes(keyBytes);

        // Encode the key in Base64 format for easier storage and usage
        return Base64.getEncoder().encodeToString(keyBytes);
    }

}
