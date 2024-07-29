package com.example.helloworldmvc.service;

import com.example.helloworldmvc.apiPayload.GeneralException;
import com.example.helloworldmvc.apiPayload.code.status.ErrorStatus;
import com.example.helloworldmvc.apiPayload.handler.UserHandler;
import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.File;
import com.example.helloworldmvc.domain.Summary;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.repository.SummaryRepository;
import com.example.helloworldmvc.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MyPageServiceImpl implements MyPageService{
    private final UserRepository userRepository;
    private final SummaryRepository summaryRepository;

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
    }

    @Override
    public Page<Summary> getSummaryList(Long userId, Integer page, Integer size) {
        userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        return summaryRepository.findAllByUserId(userId, PageRequest.of(page, size));
    }
}
