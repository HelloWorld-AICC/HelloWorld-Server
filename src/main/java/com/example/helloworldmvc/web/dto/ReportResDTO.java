package com.example.helloworldmvc.web.dto;

import com.example.helloworldmvc.domain.enums.ReportStatus;
import com.example.helloworldmvc.domain.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReportResDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReportRes{
        Long communityId;
    }
}
