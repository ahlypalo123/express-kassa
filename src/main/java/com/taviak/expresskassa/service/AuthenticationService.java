package com.taviak.expresskassa.service;

import com.taviak.expresskassa.constants.ErrorCode;
import com.taviak.expresskassa.dto.AuthDto;
import com.taviak.expresskassa.dto.CodeDto;
import com.taviak.expresskassa.entity.Merchant;
import com.taviak.expresskassa.exception.ApiException;
import com.taviak.expresskassa.repository.MerchantRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class AuthenticationService {

    private static final String JWT_TOKEN_SECRET = "vlk3Tmn8$Xc49dC%hp3O#x6pghf";
    private final Integer CODE_EXPIRATION_TIME_MIN = 2;
    private final LoadingCache<String, CodeDto> codes = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(CODE_EXPIRATION_TIME_MIN, TimeUnit.MINUTES)
            .build(new CacheLoader<>() {
                @Override
                public CodeDto load(String key) {
                    return new CodeDto(null, null);
                }
            });

    private final MerchantRepository merchantRepository;
    private final MerchantService merchantService;
    private final EmailSenderService emailSenderService;

    public AuthenticationService(MerchantRepository merchantRepository, MerchantService merchantService, EmailSenderService emailSenderService) {
        this.merchantRepository = merchantRepository;
        this.merchantService = merchantService;
        this.emailSenderService = emailSenderService;
    }

    public String login(AuthDto dto) {
        Merchant m = merchantRepository.findOneByEmail(dto.getEmail())
                .orElseThrow(() -> new ApiException("User not found", ErrorCode.USER_NOT_FOUND));
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

    public void sendConfirmationCode(String email) throws ExecutionException {
        if (codes.get(email).getCode() != null) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 1);
            if (codes.get(email).getCreated().isBefore(new DateTime(c))) {
                throw new ApiException("Please wait. Code has already been sent to this email.", ErrorCode.VERIFICATION_CODE_ALREADY_SENT);
            }
        }
        Integer code = RandomUtils.nextInt(1000, 10000);
        codes.put(email, new CodeDto(code, DateTime.now()));
        emailSenderService.sendEmail("Your confirmation code is " + code, email, "Confirmation Code");
    }

    public void validateCode(String email, Integer code) throws ExecutionException {
        Integer expected;
        expected = codes.get(email).getCode();
        if(!code.equals(expected)) {
            throw new ApiException("Invalid code", ErrorCode.INVALID_VERIFICATION_CODE);
        }
    }

}
