package com.example.helloworldmvc.converter;

import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.web.dto.CenterResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
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
}
