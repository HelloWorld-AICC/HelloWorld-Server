package com.example.helloworldmvc.service;

import com.example.helloworldmvc.apiPayload.ApiResponse;
import com.example.helloworldmvc.config.auth.GoogleClient;
import com.example.helloworldmvc.config.auth.JwtTokenProvider;
import com.example.helloworldmvc.converter.UserConverter;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.repository.UserRepository;
import com.example.helloworldmvc.web.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleServiceImpl implements GoogleService {

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.client.pw}")
    private String googleClientPassword;

    @Value("${google.login.url}")
    private String googleApiUrl;

    @Value("${google.redirect.url}")
    private String redirectUrl;

    private final GoogleClient googleClient;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate redisTemplate;
    @Override
    public ApiResponse<String> getGoogleLoginView() {
        return ApiResponse.<String>builder()
                .isSuccess(true)
                .code("200")
                .result(googleApiUrl + "client_id=" + googleClientId
                        + "&redirect_uri=" + redirectUrl
                        + "&response_type=code"
                        + "&scope=email%20profile%20openid"
                        + "&access_type=offline")
                .message("google login view url입니다.")
                .build();
    }

    @Override
    @Transactional
    public List<TokenDTO> loginGoogle(String code) {
        GoogleTokenResponse googleTokenResponse = googleClient.getGoogleToken(GoogleTokenRequest.builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPassword)
                .code(code)
                .redirectUri("http://localhost:8080/api/v1/google/login")
                .grantType("authorization_code")
                .build());
        GoogleDetailResponse googleProfile = googleClient.getGoogleDetailInfo(GoogleDetailRequest.builder()
                .id_token(googleTokenResponse.getId_token())
                .build());
        Optional<User> optionalUser = userRepository.findByEmail(googleProfile.getEmail());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            TokenDTO accessToken = jwtTokenProvider.createAccessToken(user.getEmail());
            TokenDTO refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());
            redisTemplate.opsForValue().set("RT:" + user.getEmail(), refreshToken.getToken(), refreshToken.getTokenExpriresTime().getTime(), TimeUnit.MILLISECONDS);
            List<TokenDTO> tokenDTOList = new ArrayList<>();
            tokenDTOList.add(refreshToken);
            tokenDTOList.add(accessToken);

            return tokenDTOList;
        } else {
            User user = userRepository.save(UserConverter.toGoogleUser(googleProfile));
            TokenDTO accessToken = jwtTokenProvider.createAccessToken(user.getEmail());
            TokenDTO refreshToken = jwtTokenProvider.createRefreshToken(user.getEmail());
            redisTemplate.opsForValue().set("RT:" + refreshToken, TimeUnit.MILLISECONDS);

            return (List<TokenDTO>) UserConverter.toOAuthResponse(false, accessToken, refreshToken, user);
        }
    }
}
