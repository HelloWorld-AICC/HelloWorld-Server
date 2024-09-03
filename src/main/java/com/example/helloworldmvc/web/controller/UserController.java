package com.example.helloworldmvc.web.controller;

import com.example.helloworldmvc.apiPayload.ApiResponse;
import com.example.helloworldmvc.service.GoogleService;
import com.example.helloworldmvc.web.dto.GoogleDetailResponse;
import com.example.helloworldmvc.web.dto.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/google")
@RequiredArgsConstructor
public class UserController {

    private final GoogleService googleService;

    // 구글 로그인 화면 이동
    @PostMapping("/login-view")
    @Operation(summary = "구글 로그인 화면 조회 API", description = "구글 간편 로그인 페이지로 이동")
    public ResponseEntity<ApiResponse<String>> getGoogleLoginView(){
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .body(googleService.getGoogleLoginView());
    }

    @Operation(summary = "구글 로그인 API", description = "구글 로그인 및 회원 가입을 진행")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "code", description = "query string(RequestParam) - accessToken 입력"),
    })
    @GetMapping("/login")
    public ApiResponse<List<TokenDTO>> selectGoogleLoginInfo(@RequestParam(value = "code") String code){
        return ApiResponse.onSuccess(googleService.loginGoogle(code));
    }

    @Operation(summary = "구글 로그인 인증코드 발급 API", description = "code 발급")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @GetMapping("/code")
    public ApiResponse<String> grantGoogleLoginCode(@RequestParam(value = "code") String code){
        return ApiResponse.onSuccess(code);
    }
}
