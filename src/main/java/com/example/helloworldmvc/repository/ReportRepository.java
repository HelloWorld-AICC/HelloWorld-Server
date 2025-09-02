package com.example.helloworldmvc.repository;

import com.example.helloworldmvc.domain.CommunityReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<CommunityReport, Long> {
}
