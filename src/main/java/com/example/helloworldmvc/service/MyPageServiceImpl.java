package com.example.helloworldmvc.service;

import com.example.helloworldmvc.apiPayload.GeneralException;
import com.example.helloworldmvc.apiPayload.code.status.ErrorStatus;
import com.example.helloworldmvc.apiPayload.handler.UserHandler;
import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.File;
import com.example.helloworldmvc.domain.Summary;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.domain.mapping.Reservation;
import com.example.helloworldmvc.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MyPageServiceImpl implements MyPageService{
    private final UserRepository userRepository;
    private final SummaryRepository summaryRepository;
    private final CounselorRepository counselorRepository;
    private final ReservationRepository reservationRepository;
    private final FileRepository fileRepository;
    private final S3Service s3Service;



    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
    }

    @Override
    public Summary getSummary(Long summaryId) {
        return summaryRepository.findById(summaryId).orElseThrow(() -> new GeneralException(ErrorStatus.SUMMARY_NOT_FOUND));
    }

    @Override
    public Page<Summary> getSummaryList(Long userId, Integer page, Integer size) {
        userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        return summaryRepository.findAllByUserId(userId, PageRequest.of(page, size));
    }

    @Override
    public Page<Reservation> getReservationList(Long counselorId, Integer page, Integer size) {
        counselorRepository.findById(counselorId).orElseThrow(() -> new GeneralException(ErrorStatus.COUNSELOR_NOT_FOUND));
        return reservationRepository.findAllByCounselorId(counselorId, PageRequest.of(page, size));
    }

    @Override
    public void setUserProfile(Long userId, MultipartFile file){
        User user=userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

//        Long userId = jwtTokenProvider.getCurrentUser(request);
//
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isEmpty()) {
//            userService.checkUser(false);
//        }
//        User user = optionalUser.get();

        Optional<File> optionalFile=fileRepository.findByUserId(userId);
        File newFile=null;
        if(optionalFile.isPresent()){
            newFile=s3Service.changeImage(file,user);
        }else{
            newFile=s3Service.setImage(file,user);
        }
        user.setFile(newFile);
    }
}
