package com.example.helloworldmvc.service;

import com.example.helloworldmvc.apiPayload.GeneralException;
import com.example.helloworldmvc.apiPayload.code.status.ErrorStatus;
import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.repository.CenterRepository;
import com.example.helloworldmvc.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CenterServiceImpl implements CenterService{

    private final UserRepository userRepository;
    private final CenterRepository centerRepository;
    @Override
    public Page<Center> getCenterList(Long userId, Integer page, Integer size) {
        userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        return centerRepository.findAll(PageRequest.of(page, size));
    }
}
