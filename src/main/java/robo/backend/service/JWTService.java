package robo.backend.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private String secreteKey;

    public JWTService () throws NoSuchAlgorithmException{
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey key = keyGen.generateKey();
        secreteKey = Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public String generateToken(String username) {

        Map <String, Object> claims = new HashMap<>();

        return Jwts.builder()
                  .claims()
                  .add(claims)
                  .subject(username)
                  .issuedAt(new Date(System.currentTimeMillis()))
                  .expiration(new Date(System.currentTimeMillis() + 60 * 15 * 1000))
                  .and()
                  .signWith(getKey())
                  .compact();
    }

    public SecretKey getKey() {
         byte[] keyByte = Decoders.BASE64.decode(secreteKey);
         return Keys.hmacShaKeyFor(keyByte);
    }

    public String extractUsername(String jwtToken){
        return extractClaim(jwtToken, Claims::getSubject);
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims (String jwtToken) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public boolean validationToken (String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
    }

    private boolean isTokenExpired (String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration (String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }


}
