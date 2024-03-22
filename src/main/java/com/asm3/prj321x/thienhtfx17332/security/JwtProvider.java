package com.asm3.prj321x.thienhtfx17332.security;

import com.asm3.prj321x.thienhtfx17332.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Key;
import java.util.Date;


@Configuration
@Log4j2
public class JwtProvider {

    @Autowired
    private HttpServletRequest httpRequest;

    private String AUTHORIZATION_HEADER = "Authorization";

    @Value("${application.security.secretkey}")
    private String jwtBase64Secret;
    @Value("${application.security.jwt.token-validity-in-seconds}")
    private long tokenValidityInSeconds;
    private Key key;
    private JwtParser jwtParser;

    @PostConstruct
    public void setup() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtBase64Secret);
        key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parser().setSigningKey(key).build();
    }

    public String createToken(User user) {
        return Jwts
                .builder()
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getEmail())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date((new Date()).getTime() + 1000 * this.tokenValidityInSeconds))
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
        }
        return false;
    }

    public Integer getUserFromToken() {
        try {
            var bearerToken = httpRequest.getHeader(AUTHORIZATION_HEADER);
            var jwt = JwtUtils.resolveToken(bearerToken);
            var jwtParse = jwtParser.parseClaimsJws(jwt);
            return Integer.valueOf(jwtParse.getPayload().getId());
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
        }
        return null;
    }



}
