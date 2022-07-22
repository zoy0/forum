package com.forum.projectlzy.utils;

import com.forum.projectlzy.entity.User;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static final long SURVIVAL_TIME = 1000*60*60*24*7;

    private static final String SIGNATURE = "admin_lzy";

    public static String build(User user){
        JwtBuilder jwtBuilder= Jwts.builder();
        return jwtBuilder.setHeaderParam("tpy","JWT")
                .setHeaderParam("alg","HS256")
                .claim("id",user.getId())
                .claim("username",user.getUserName())
                .claim("isAdmin",user.getAdmin())
                .setSubject("user-forum")
                .setExpiration(new Date(System.currentTimeMillis()+SURVIVAL_TIME))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,SIGNATURE)
                .compact();
    }

    public static User parse(String token){
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(SIGNATURE).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        User user = new User();
        user.setUserName((String) body.get("username"));
        user.setId((Integer) body.get("id"));
        user.setAdmin((Integer)body.get("isAdmin"));
        return user;
    }

    public static boolean isExpired(String token){
        JwtParser jwtParser=Jwts.parser();
        Claims body = jwtParser.setSigningKey(SIGNATURE).parseClaimsJws(token).getBody();
        Date expiration = body.getExpiration();
        return new Date(System.currentTimeMillis()).before(expiration);
    }


}
