package com.example.helloworldmvc.service;

import com.example.helloworldmvc.apiPayload.ApiResponse;
import com.example.helloworldmvc.web.dto.GoogleDetailResponse;
import com.example.helloworldmvc.web.dto.TokenDTO;

import java.util.List;

public interface GoogleService {
    ApiResponse<String> getGoogleLoginView();

    List<TokenDTO> loginGoogle(String code);
}
