package com.example.helloworldmvc.web.controller;

import com.example.helloworldmvc.apiPayload.ApiResponse;
import com.example.helloworldmvc.converter.CenterConverter;
import com.example.helloworldmvc.converter.MyPageConverter;
import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.Summary;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.service.CenterService;
import com.example.helloworldmvc.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/")
    @Operation(summary = "마이페이지 API", description = "마이페이지 화면 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "사용자를 찾을수 없습니다.")
    })
    @Parameters({
            @Parameter(name = "user_id", description = "RequestHeader - 로그인한 사용자 아이디(accessToken으로 변경 예정)"),
    })
    public ApiResponse<?> getMyPage(@RequestHeader("user_id") Long userId){
        User user=myPageService.getUser(userId);
        return ApiResponse.onSuccess(MyPageConverter.toMyPageRes(user));
    }

    @GetMapping("/AllConsultations")
    @Operation(summary = "전체 상담 히스토리 API", description = "(외국인)내 전체 상담 히스토리 화면 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "사용자를 찾을수 없습니다.")
    })
    @Parameters({
            @Parameter(name = "user_id", description = "RequestHeader - 로그인한 사용자 아이디(accessToken으로 변경 예정)"),
            @Parameter(name = "page", description = "query string(RequestParam) - 몇번째 페이지인지 가리키는 page 변수 입니다! (0부터 시작)"),
            @Parameter(name = "size", description = "query string(RequestParam) - 몇 개씩 불러올지 개수를 세는 변수입니다. (1 이상 자연수로 설정)"),
    })
    public ApiResponse<?> getAllSummary(@RequestHeader("user_id") Long userId,
                                        @RequestParam(name = "page") Integer page,
                                        @RequestParam(name = "size") Integer size){
        Page<Summary> summaryList = myPageService.getSummaryList(userId, page, size);
        return ApiResponse.onSuccess(MyPageConverter.toAllSummaryListRes(summaryList));
    }

}
