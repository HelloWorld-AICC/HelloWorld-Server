package com.example.helloworldmvc.service;

import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.Summary;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.domain.mapping.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface MyPageService {
    User getUser(Long userId);
    Summary getSummary(Long summaryId);
    Page<Summary> getSummaryList(Long userId, Integer page, Integer size);
    Page<Reservation> getReservationList(Long counselorId, Integer page, Integer size);

    void setUserProfile(Long userId, MultipartFile file);
}
