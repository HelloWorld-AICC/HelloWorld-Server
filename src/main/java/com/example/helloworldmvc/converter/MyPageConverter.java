package com.example.helloworldmvc.converter;

import com.example.helloworldmvc.apiPayload.GeneralException;
import com.example.helloworldmvc.apiPayload.code.status.ErrorStatus;
import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.File;
import com.example.helloworldmvc.domain.Summary;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.repository.UserRepository;
import com.example.helloworldmvc.web.dto.CenterResponseDTO;
import com.example.helloworldmvc.web.dto.MyPageResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MyPageConverter {
    public static MyPageResponseDTO.MyPageResDTO toMyPageRes(User user){
        String userImg = null;

        Optional<File> userFileOptional = Optional.ofNullable(user.getFile());

        if (userFileOptional.isPresent()) {
            userImg = userFileOptional.get().getUrl();
        }
        return MyPageResponseDTO.MyPageResDTO.builder()
                .name(user.getName())
                .userImg(userImg)
                .build();

    }

    public static MyPageResponseDTO.AllSummaryRes toAllSummaryRes(Summary summary) {
        User user = summary.getUser();
        String userImg = null;
        Optional<File> userFileOptional = Optional.ofNullable(user.getFile());
        if (userFileOptional.isPresent()) {
            userImg = userFileOptional.get().getUrl();
        }
        return MyPageResponseDTO.AllSummaryRes.builder()
                .summaryId(summary.getId())
                .identificationNum(summary.getIdentificationNum())
                .uploadedAt(summary.getUpdateAt())
                .name(user.getName())
                .userImg(userImg)
                .title(summary.getTitle())
                .build();
    }

    public static MyPageResponseDTO.AllSummaryListRes toAllSummaryListRes(Page<Summary> summaryList) {
        List<MyPageResponseDTO.AllSummaryRes> allSummaryRes = summaryList.stream()
                .map(MyPageConverter::toAllSummaryRes).collect(Collectors.toList());
        return MyPageResponseDTO.AllSummaryListRes.builder()
                .allsummaryList(allSummaryRes)
                .build();
    }

    public static MyPageResponseDTO.DetailSummaryRes toDetailSummaryRes(Summary summary){
        User user=summary.getUser();
        String userImg = null;
        Optional<File> userFileOptional = Optional.ofNullable(user.getFile());
        if (userFileOptional.isPresent()) {
            userImg = userFileOptional.get().getUrl();
        }
        return MyPageResponseDTO.DetailSummaryRes.builder()
                .summaryId(summary.getId())
                .identificationNum(summary.getIdentificationNum())
                .uploadedAt(summary.getUpdateAt())
                .name(user.getName())
                .userImg(userImg)
                .chatSummary(summary.getChatSummary())
                .mainPoint(summary.getMainPoint())
                .build();
    }
}
//    Long summaryId;
//    String identificationNum;
//    LocalDateTime uploadedAt;
//    String name;
//    String userImg;
//    String chatSummary;
//    String mainPoint;