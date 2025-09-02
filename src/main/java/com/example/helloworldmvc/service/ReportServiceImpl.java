package com.example.helloworldmvc.service;

import com.example.helloworldmvc.apiPayload.GeneralException;
import com.example.helloworldmvc.apiPayload.code.status.ErrorStatus;
import com.example.helloworldmvc.converter.ReportConverter;
import com.example.helloworldmvc.domain.CommunityReport;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.domain.enums.ReportStatus;
import com.example.helloworldmvc.repository.ReportRepository;
import com.example.helloworldmvc.repository.UserRepository;
import com.example.helloworldmvc.web.dto.ReportResDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportServiceImpl implements ReportService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    @Override
    public ReportResDTO.ReportRes createReport(String userId, Long communityId) {
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        userRepository.findById(communityId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        CommunityReport report = ReportConverter.toReport(communityId, ReportStatus.PENDING);
        report.setUser(user);
        CommunityReport save = reportRepository.save(report);
        return ReportConverter.toReportResDTO(save);
    }
}
