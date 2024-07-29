package com.example.helloworldmvc.converter;

import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.Counselor;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.web.dto.CenterRequestDTO;
import com.example.helloworldmvc.web.dto.CenterResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CenterConverter {
    public static CenterResponseDTO.CenterMapRes toCenterMapRes(Center center) {
        return CenterResponseDTO.CenterMapRes.builder()
                .name(center.getName())
                .status(center.getStatus())
                .closed(center.getDeadLine())
                .address(center.getAddress())
                .image(center.getFile().getUrl())
                .latitude(center.getLatitude())
                .longitude(center.getLongitude())
                .build();
    }

    public static CenterResponseDTO.CenterMapListRes toCenterMapListRes(Page<Center> centerList) {
        List<CenterResponseDTO.CenterMapRes> centerMapRes = centerList.stream()
                .map(CenterConverter::toCenterMapRes).collect(Collectors.toList());
        return CenterResponseDTO.CenterMapListRes.builder()
                .centerMapList(centerMapRes)
                .build();
    }

    public static CenterResponseDTO.CounselorListRes toCounselorListRes(Page<Counselor> counselorList) {
        List<CenterResponseDTO.CounselorRes> counselorResList = counselorList.stream()
                .map(CenterConverter::toCounselorRes).collect(Collectors.toList());
        return CenterResponseDTO.CounselorListRes.builder()
                .today(LocalDateTime.now())
                .counselorList(counselorResList)
                .build();
    }
    public static CenterResponseDTO.CounselorRes toCounselorRes(Counselor counselor) {
        return CenterResponseDTO.CounselorRes.builder()
                .name(counselor.getName())
                .centerName(counselor.getCenter().getName())
                .language(counselor.getCounselorLanguageList().stream().map(s -> s.getLanguage().getName()).collect(Collectors.toList()))
                .start(counselor.getStart())
                .end(counselor.getEnd())
                .build();
    }

    public static CenterResponseDTO.FilterRes toFilterRes(User user) {
        return CenterResponseDTO.FilterRes.builder()
                .userId(user.getId())
                .build();
    }

}
