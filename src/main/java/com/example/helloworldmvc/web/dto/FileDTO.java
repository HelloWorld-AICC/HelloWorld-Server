package com.example.helloworldmvc.web.dto;

import lombok.*;

@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    private Long imageId;
    private String imageUrl;
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileDetailRes{
        String fileUrl;
        String fileType;
    }
}