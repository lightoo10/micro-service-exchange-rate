package org.bcp.banking.exchange.rate.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.bcp.banking.exchange.rate.util.Constants.BEARER;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire-length:3600000}")
    private long expireLength;

    public DecodedJWT getDecodedJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        return verifier.verify(token);
    }

    public Claim getClaim(DecodedJWT decode, String key) {
        return decode.getClaim(key);
    }

    public Boolean validateToken(DecodedJWT decode) {
        return !decode.getExpiresAt().before(new Date());
    }

    public String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("username", username);
        claims.put("auth", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        String token = Jwts
                .builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireLength))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()).compact();
        return token;
    }
}
