package com.example.helloworldmvc.service;

import com.example.helloworldmvc.apiPayload.GeneralException;
import com.example.helloworldmvc.apiPayload.code.status.ErrorStatus;
import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.Counselor;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.domain.mapping.UserLanguage;
import com.example.helloworldmvc.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CenterServiceImpl implements CenterService{

    private final UserRepository userRepository;
    private final CenterRepository centerRepository;
    private final LanguageRepository languageRepository;
    private final CounselorRepository counselorRepository;
    @Override
    public Page<Center> getCenterList(Long userId, Integer page, Integer size) {
        userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        return centerRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Counselor> getCounselorList(Long userId, Integer page, Integer size) {
        User user = userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        List<Long> languageId = user.getUserLanguageList().stream()
                .map(language -> language.getLanguage().getId()).collect(Collectors.toList());
        return counselorRepository.findAllByCounselorLanguageList(languageId, PageRequest.of(page, size));

    }
}
