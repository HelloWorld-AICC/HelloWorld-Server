package com.example.helloworldmvc.converter;

import com.example.helloworldmvc.domain.CommunityReport;
import com.example.helloworldmvc.domain.enums.ReportStatus;
import com.example.helloworldmvc.domain.enums.ReportType;
import com.example.helloworldmvc.web.dto.ReportResDTO;

public class ReportConverter {
    public static CommunityReport toReport(Long communityId, ReportStatus reportStatus) {
        return CommunityReport.builder()
                .reportStatus(reportStatus)
                .build();
    }
    public static ReportResDTO.ReportRes toReportResDTO(CommunityReport communityReport) {
        return ReportResDTO.ReportRes.builder()
                .communityId(communityReport.getCommunityId())
                .build();
    }
}
