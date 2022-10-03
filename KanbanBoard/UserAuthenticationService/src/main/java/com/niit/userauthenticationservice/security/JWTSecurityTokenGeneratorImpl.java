package com.niit.userauthenticationservice.security;


import com.niit.userauthenticationservice.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

    public Map<String, String> generateToken(User user) {
// multiple claims for a token - 3 types - registered, public, and private
        String jwtToken = Jwts.builder().setIssuer("ShopZone")
                .setSubject(String.valueOf(user.getEmailId()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"mysecret")
                .compact();
        Map<String,String> map = new HashMap<>();
        map.put("token",jwtToken);
        map.put("message","Authentication Successful");
        return map;
    }
}
