package com.example.helloworldmvc.web.controller;

import com.example.helloworldmvc.apiPayload.ApiResponse;
import com.example.helloworldmvc.config.auth.JwtTokenProvider;
import com.example.helloworldmvc.service.CommunityService;
import com.example.helloworldmvc.web.dto.CommunityRequestDTO;
import com.example.helloworldmvc.web.dto.CommunityResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final JwtTokenProvider jwtTokenProvider;
    private final CommunityService communityService;
    @PostMapping(value = "/{category_id}/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "커뮤니티 글 작성 API", description = "커뮤니티 게시판에 글을 작성하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "Authorization", description = "RequestHeader - 로그인한 사용자 토큰"),
    })
    public ApiResponse<CommunityResponseDTO.CreatedPostDTO> createCommunity(@RequestHeader(name = "Authorization") String accessToken,
                                                                            @PathVariable(name = "category_id") Long categoryId,
                                                                            @ModelAttribute @Valid CommunityRequestDTO.CreatePostDTO request){
        String gmail = jwtTokenProvider.getGoogleEmail(accessToken);
        return ApiResponse.onSuccess(communityService.createCommunityPost(gmail, categoryId, request));
    }
}
