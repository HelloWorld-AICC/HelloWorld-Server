package com.example.helloworldmvc.web.controller;

import com.example.helloworldmvc.apiPayload.ApiResponse;
import com.example.helloworldmvc.converter.CenterConverter;
import com.example.helloworldmvc.converter.MyPageConverter;
import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.Summary;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.domain.mapping.Reservation;
import com.example.helloworldmvc.service.CenterService;
import com.example.helloworldmvc.service.MyPageService;
import com.example.helloworldmvc.web.dto.CenterRequestDTO;
import com.example.helloworldmvc.web.dto.CenterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPage")
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

    @GetMapping("/allSummary")
    @Operation(summary = "전체 상담 조회 API", description = "(외국인)내 전체 상담 조회 화면 API입니다.")
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
        return ApiResponse.onSuccess(MyPageConverter.toAllSummaryListRes(summaryList,userId));
    }

    @GetMapping("/detailSummary")
    @Operation(summary = "상세 상담 조회 API", description = "(외국인,상담사)상세 상담 조회 화면 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "사용자를 찾을수 없습니다.")
    })
    public ApiResponse<?> getDetailSummary(@RequestParam("summary-id") Long summaryId){
        Summary summary=myPageService.getSummary(summaryId);

        return ApiResponse.onSuccess(MyPageConverter.toDetailSummaryRes(summary));
    }

    @GetMapping("/allReservation")
    @Operation(summary = "상담 신청 내역 조회 API", description = "(상담사)상담 신청 내역 조회 화면 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "사용자를 찾을수 없습니다.")
    })
    @Parameters({
            @Parameter(name = "counselor_id", description = "RequestHeader - 로그인한 상담사 아이디(accessToken으로 변경 예정)"),
            @Parameter(name = "page", description = "query string(RequestParam) - 몇번째 페이지인지 가리키는 page 변수 입니다! (0부터 시작)"),
            @Parameter(name = "size", description = "query string(RequestParam) - 몇 개씩 불러올지 개수를 세는 변수입니다. (1 이상 자연수로 설정)"),
    })
    public ApiResponse<?> getAllReservation(@RequestHeader("counselor_id") Long userId,
                                        @RequestParam(name = "page") Integer page,
                                        @RequestParam(name = "size") Integer size){
        Page<Reservation> reservationList = myPageService.getReservationList(userId, page, size);
        return ApiResponse.onSuccess(MyPageConverter.toAllReservationListRes(reservationList,userId));
    }

    @PostMapping(value="/setProfile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "프로필 변경 API", description = "프로필 변경 API API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),

    })
    @Parameters({
            @Parameter(name = "user_id", description = "RequestHeader - 로그인한 사용자 아이디(accessToken으로 변경 예정)")
    })
    public ApiResponse<Long> createLanguageFilter(@RequestHeader("user_id") Long userId,
                                                                         @RequestParam("file") MultipartFile file) {
        myPageService.setUserProfile(userId, file);
        return ApiResponse.onSuccess(userId);
    }
}
