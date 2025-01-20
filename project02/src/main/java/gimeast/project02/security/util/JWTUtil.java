package gimeast.project02.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Log4j2
public class JWTUtil {

    private final String SECRET_KEY = "strongAndSafeSecretKeyForJWTUtilTest_12345678";
    private final long EXPIRE = 60 * 24 * 30; //1month
    private final MacAlgorithm hs256 = Jwts.SIG.HS256;

    public String generateToken(String content) throws Exception {
        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(Date.from(ZonedDateTime.now().plusMinutes(EXPIRE).toInstant()))
//                .expiration(Date.from(ZonedDateTime.now().plusSeconds(1).toInstant())) //테스트용
                .claim("sub", content)
                .signWith(getSignInKey(), hs256)
                .compact();
    }

    public String validateAndExtract(String tokenStr) throws Exception {
        String contentValue = null;

        try {
            contentValue = Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(tokenStr)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            log.error("Token validation failed: {}", e.getMessage());
        }

        return contentValue;
    }

    private SecretKey getSignInKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8),
                hs256.key().build().getAlgorithm());
    }
}
