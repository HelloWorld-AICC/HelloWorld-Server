package com.example.helloworldmvc.web.dto;

import com.example.helloworldmvc.domain.File;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.domain.enums.SummaryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class MyPageResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageResDTO{
        String name;
        String userImg;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AllSummaryListRes {
        List<MyPageResponseDTO.AllSummaryRes> allsummaryList;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AllSummaryRes{
        Long summaryId;
        String identificationNum;
        LocalDateTime uploadedAt;
        String name;
        String userImg;
        String title;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailSummaryRes{
        Long summaryId;
        String identificationNum;
        LocalDateTime uploadedAt;
        String name;
        String userImg;
        String chatSummary;
        String mainPoint;
    }
}
