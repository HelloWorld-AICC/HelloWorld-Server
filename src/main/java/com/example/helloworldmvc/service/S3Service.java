package com.example.helloworldmvc.service;

import com.example.helloworldmvc.apiPayload.aws.s3.AmazonS3Manager;
import com.example.helloworldmvc.converter.FileConverter;
import com.example.helloworldmvc.domain.Community;
import com.example.helloworldmvc.domain.File;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.domain.Uuid;
import com.example.helloworldmvc.repository.FileRepository;
import com.example.helloworldmvc.repository.UuidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
@Service
@Transactional
@RequiredArgsConstructor
public class S3Service {
    private final UuidRepository uuidRepository;
    private final AmazonS3Manager s3Manager;
    private final FileRepository fileRepository;


    public File setImage(MultipartFile file, User user){
        String pictureUrl = s3Manager.uploadFile(s3Manager.generateUserKeyName(createFileName()), file);
        String fileType = this.getFileExtension(file);
        return fileRepository.save(FileConverter.toFile(pictureUrl, fileType ,user, null));
    }

    @Transactional
    public File changeImage(MultipartFile file, User user) {
        File newFile;
        String pictureUrl = s3Manager.uploadFile(s3Manager.generateUserKeyName(createFileName()), file);
        newFile = fileRepository.findByUserId(user.getId()).get();
        newFile.setUrl(pictureUrl);
        return newFile;
    }

    public File setCommunityImage(MultipartFile file, Community community) {
        String url = s3Manager.uploadFile(s3Manager.generateUserKeyName(createFileName()), file);
        String fileType = this.getFileExtension(file);
        File file1 = FileConverter.toFile(url, fileType,null, null);
        file1.setCommunity(community);
        return fileRepository.save(file1);
    }
    public Uuid createFileName() {
        String uuid = UUID.randomUUID().toString();
        return uuidRepository.save(Uuid.builder().uuid(uuid).build());
    }
    /**
     * 파일 확장자 가져오기
     * @author 이승우
     * @param file MultipartFile 객체
     * @return 파일 확장자 (예: ".jpg", ".png")
     */
    public String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return ""; // 확장자가 없는 경우 빈 문자열 반환 또는 예외 처리
    }
}
