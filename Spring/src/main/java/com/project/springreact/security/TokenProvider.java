package com.project.springreact.security;

import com.project.springreact.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY = "INHe5oeRO4amRoCpXno5iQcyYGZ9PyYGZeL+tjRocS1OIM+sXPPNpx51lE2qheq/GZhKxICuP/uRnMCXW2GkURM=";
    public String create(User userEntity) {
        // 기한 - 1일
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS));
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        // JWT Token 생성
        return Jwts.builder()
                    .signWith(key,SignatureAlgorithm.HS512)
                    .setSubject(userEntity.getId())// sub
                    .setIssuer("Todo app")//iss
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .compact();
        }
    public String validateAndGetUserId(String token){
        //parseClaimJWws method 가 base64로 디코딩- 파싱이 이루어진다.
        // 헤더- 페이로드를 setSigningkey 로 넘어온 시크릿을 이용해서명 -token 과 비교
        // 위조 되지 안항ㅆ으면 페이로드 리턴, 위조면 예외
        // 여기서 getBody 로 Body에 있는 Userid 를 가져온다.
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        log.debug("TokenProvider: token: {}", token);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();

    }
}
