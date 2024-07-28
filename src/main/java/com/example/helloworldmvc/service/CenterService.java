package com.example.helloworldmvc.service;

import com.example.helloworldmvc.domain.Center;
import org.springframework.data.domain.Page;

public interface CenterService {
    Page<Center> getCenterList(Long userId, Integer page, Integer size);
}
