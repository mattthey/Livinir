package naumen.livinir.service;

import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Управление JWt токенами
 * - генерация
 * - валидация
 * - извлечение email
 */
@Component
public class JwtServiceImpl
{

    private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Value("${grokonez.app.jwtSecret}")
    private String jwtSecret;

    @Value("${grokonez.app.jwtExpiration}")
    private int jwtExpiration;

    @Autowired
    private ResidentServiceImpl residentService;

    /**
     * Генерация accessToken токена
     * @param email
     * @return строка JWT токен
     */
    public String generateJwtAccessToken(String email)
    {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Генерация refreshToken токена
     * @param email
     * @return строка JWT токен
     */
    public String generateJwtRefreshToken(String email)
    {
        String refreshToken = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return refreshToken;
    }

    public Map<String, String> generateTokens(String email)
    {
        String refreshToken = generateJwtRefreshToken(email);
        String accessToken = generateJwtAccessToken(email);

        return ImmutableMap.of("accessToken", accessToken, "refreshToken", refreshToken);
    }

    public Map<String, String> updateTokens(String email)
    {
        return Collections.emptyMap();
    }

    /**
     * извлекает имя пользователя из JWT токена
     * @param token строка - JWT токен
     * @return
     */
    public String getUserNameFromJwtToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    /**
     *
     * @param authToken

     * @return
     */
    public boolean validateJwtToken(String authToken)
    {
        try
        {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }
        catch (Exception e)
        {
            logger.error("Error validate JWT token -> Message: {} ", e);
        }
        return false;
    }

    /**
     * Получение jwt токена из хэдеров
     * @param request запрос
     * @return JWT токен
     */
    private String getJwt(HttpServletRequest request)
    {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer "))
        {
            return authHeader.replace("Bearer ","");
        }
        return null;
    }
}