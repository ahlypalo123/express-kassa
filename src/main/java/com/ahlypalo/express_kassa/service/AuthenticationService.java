package com.ahlypalo.express_kassa.service;

import com.ahlypalo.express_kassa.dto.AuthDto;
import com.ahlypalo.express_kassa.entity.Merchant;
import com.ahlypalo.express_kassa.exception.ApiException;
import com.ahlypalo.express_kassa.repository.MerchantRepository;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AuthenticationService {

    private static final String JWT_TOKEN_SECRET = "vlk3Tmn8$Xc49dC%hp3O#x6pghf";

    private final MerchantRepository merchantRepository;
    private final MerchantService merchantService;

    public AuthenticationService(MerchantRepository merchantRepository, MerchantService merchantService) {
        this.merchantRepository = merchantRepository;
        this.merchantService = merchantService;
    }

    public String login(AuthDto dto) {
        Merchant m = merchantRepository.findOneByEmail(dto.getEmail())
                .orElseThrow(() -> new ApiException("User not found"));
        return createToken(m.getEmail());
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    public String createToken(String email) {
        JwtBuilder builder = Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS512,
                    JWT_TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));
        return builder.compact();
    }

    public void register(AuthDto dto) {
        Merchant m = new Merchant();
        m.setPassword(dto.getPassword());
        m.setEmail(dto.getEmail());
        merchantService.saveMerchant(m);
    }

}
