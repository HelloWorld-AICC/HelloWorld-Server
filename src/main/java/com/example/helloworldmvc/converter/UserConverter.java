package com.example.helloworldmvc.converter;

import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.web.dto.GoogleDetailResponse;
import com.example.helloworldmvc.web.dto.TokenDTO;
import com.example.helloworldmvc.web.dto.UserResponseDTO;

public class UserConverter {

    public static User toGoogleUser(GoogleDetailResponse googleProfile){
        return User.builder()
                .email(googleProfile.getEmail())
                .name(googleProfile.getName())
                .build();
    }
    public static UserResponseDTO.OAuthResponse toOAuthResponse(TokenDTO accessToken, TokenDTO refreshToken, Boolean isLogin, User user) {
        return UserResponseDTO.OAuthResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .isLogin(isLogin)
                .email(user.getEmail())
                .build();
    }
}
