package com.example.helloworldmvc.web.controller;

import com.example.helloworldmvc.apiPayload.ApiResponse;
import com.example.helloworldmvc.config.auth.JwtTokenProvider;
import com.example.helloworldmvc.service.ReportService;
import com.example.helloworldmvc.web.dto.CommentRequestDTO;
import com.example.helloworldmvc.web.dto.CommentResponseDTO;
import com.example.helloworldmvc.web.dto.ReportResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "JWT Token")
@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final JwtTokenProvider jwtTokenProvider;
    private final ReportService reportService;

    @PostMapping(value = "/community/{community_id}")
    @Operation(summary = "커뮤니티 신고 API", description = "커뮤니티 신고 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "사용자를 찾을수 없습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMUNITY4001", description = "커뮤니티 글이 존재하지 않습니다.")
    })
    @Parameters({
            @Parameter(name = "Authorization", description = "RequestHeader - 로그인한 사용자 토큰"),
            @Parameter(name = "community_id ", description = "PathVariable - 커뮤니티 글 아이디"),
    })
    public ApiResponse<ReportResDTO.ReportRes> createCommunityReport(@RequestHeader(name = "Authorization") String accessToken,
                                                                     @PathVariable(name = "community_id") Long targetId){
            String gmail = jwtTokenProvider.getGoogleEmail(accessToken);
            return ApiResponse.onSuccess(reportService.createReport(gmail, targetId));
    }
}
