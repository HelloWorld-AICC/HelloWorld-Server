package com.example.helloworldmvc.service;


import com.example.helloworldmvc.web.dto.ReportResDTO;

public interface ReportService {
    public ReportResDTO.ReportRes createReport(String userId, Long communityId);

}
