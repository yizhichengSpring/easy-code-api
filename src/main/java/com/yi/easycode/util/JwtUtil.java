package com.yi.easycode.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yizhicheng
 * @ClassName JwtUtil
 * @Description JWT工具类
 * @Date 2020/12/20 7:48 下午
 **/
@Slf4j
@Data
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expireTime}")
    private Long expireTime;
    @Value("${jwt.headerKeyPrefix}")
    private String headerKeyPrefix;
    @Value("${jwt.headerValuePrefix}")
    private String headerValuePrefix;

    /**
     * 生成token
     * @param name
     * @return
     */
    public String generateJwtToken(String name) {
        Map<String, Object> userInfo = new HashMap<>(16);
        userInfo.put(Claims.SUBJECT,name);
        userInfo.put("createdTime",System.currentTimeMillis());
        return Jwts
                .builder()
                .addClaims(userInfo)
                .setExpiration(setExpirTime())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 获取承载
     * @param token
     * @return
     */
    public Claims getClaims(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 获取对应用户名
     * @param token
     * @return
     */
    public String getUserName(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get(Claims.SUBJECT);
    }

    /**
     * 判断有效期 false 代表已过期
     * @return
     */
    public boolean isExpire(String token) {
        Claims claims = getClaims(token);
        Date expireTime = claims.getExpiration();
        return expireTime.compareTo(new Date()) > 0;
    }

    public  Date setExpirTime() {
        return new Date(System.currentTimeMillis() + expireTime);
    }

}
