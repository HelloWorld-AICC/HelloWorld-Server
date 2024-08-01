package com.example.helloworldmvc.service;

import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.Counselor;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.domain.mapping.UserLanguage;
import com.example.helloworldmvc.web.dto.CenterRequestDTO;
import org.springframework.data.domain.Page;

public interface CenterService {
//    Page<Center> getCenterList(Long userId, Integer page, Integer size);

    Page<Counselor> getCounselorList(Long userId, Long centerid, Integer page, Integer size);

    User createUserLanguage(Long userId, Long centerId, CenterRequestDTO.FilterLanguageReq request);

    Center getCenter(Long userId, Long centerId);

    Page<Center> getCenterListByDistance(double latitude, double longitude, Integer page, Integer size);
}
