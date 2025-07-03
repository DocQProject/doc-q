package api.docq.config.security;

import api.docq.domain.user.enums.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.rmi.ServerException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;

    //todo: 테스트를 위해 12시간으로 설정 추후 짧게 설정하기
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 12 * 60 * 60 * 1000L; //12시간

    //todo: 추후 refresh 토큰 추가하기
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000L; //일주일
    private static final String BEARER_PREFIX = "Bearer ";

    @PostConstruct
    public void init() {
        byte[] decodeBytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(decodeBytes);
    }

    public String createAccessToken(Long userId, String loginId, UserRole role) {
        return Jwts.builder()
                .claim("id", userId)
                .claim("loginId", loginId)
                .claim("role", role)
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .issuedAt(new Date())
                .signWith(key)
                .compact();
    }

    public String substringToken(String tokenValue) throws ServerException {
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            return tokenValue.substring(7);
        }
        throw new ServerException("Not Found Token");
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
