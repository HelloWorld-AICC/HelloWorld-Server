package com.example.helloworldmvc.service;

import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.Summary;
import com.example.helloworldmvc.domain.User;
import org.springframework.data.domain.Page;

public interface MyPageService {
    User getUser(Long userId);
    Page<Summary> getSummaryList(Long userId, Integer page, Integer size);

}
