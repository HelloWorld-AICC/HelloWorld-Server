package com.example.helloworldmvc.converter;

import com.example.helloworldmvc.domain.Center;
import com.example.helloworldmvc.domain.Community;
import com.example.helloworldmvc.domain.File;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.web.dto.FileDTO;

public class FileConverter {
    public static File toFile(String pictureUrl, String fileType, User user, Center center) {
        return File.builder()
                .url(pictureUrl)
                .fileType(fileType)
                .user(user)
                .center(center)
                .build();
    }
    public static FileDTO.FileDetailRes toFileDetailRes(String pictureUrl, String fileType) {
        return FileDTO.FileDetailRes.builder()
                .fileUrl(pictureUrl)
                .fileType(fileType)
                .build();
    }
}
