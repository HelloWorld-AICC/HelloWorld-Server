package com.example.helloworldmvc.web.dto;

import com.example.helloworldmvc.domain.enums.CenterStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CenterResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CenterMapListRes {
        List<CenterResponseDTO.CenterMapRes> centerMapList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CenterMapRes {
        String name;
        CenterStatus status;
        LocalDateTime closed;
        String address;
        //MultipartFile 형으로 변경예정
        String image;
        Double latitude;
        Double longitude;
    }
}
