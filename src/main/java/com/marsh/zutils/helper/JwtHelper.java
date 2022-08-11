package com.marsh.zutils.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.marsh.zutils.exception.BaseBizException;
import com.marsh.zutils.exception.BaseErrorCode;
import com.marsh.zutils.property.JwtProperties;
import com.marsh.zutils.util.DateUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;


@Component
public class JwtHelper {

    private final JwtProperties jwtProperties;

    public JwtHelper(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public  String encode(String subject, Integer expireDays, String audience, Map<String, String> claims) {
        Map<String, Object> header = Map.of(
                "alg", jwtProperties.getAlg(),
                "typ", "JWT"
        );
        var now = LocalDateTime.now();
        try {
            var algorithm = Algorithm.HMAC256(jwtProperties.getSecret());
            var jb = JWT.create()
                    .withIssuer(jwtProperties.getIssuer())
                    .withHeader(header)
                    .withIssuedAt(DateUtil.toDate(now))
                    .withSubject(subject)
                    .withAudience(audience)
                    .withExpiresAt(DateUtil.toDate(now.plusDays(expireDays)))
                    .withAudience();
            claims.forEach(jb::withClaim);
            return jb.sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }

    public  DecodedJWT decode(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecret()); //use more secure key
            var verifier = JWT.require(algorithm)
                    .withIssuer(jwtProperties.getIssuer())
                    .build(); //Reusable verifier instance
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new BaseBizException(BaseErrorCode.builder()
                    .code("000010")
                    .msg(exception.getMessage())
                    .build());
        }
    }

}
