package com.example.helloworldmvc.service;

import com.example.helloworldmvc.apiPayload.GeneralException;
import com.example.helloworldmvc.apiPayload.code.status.ErrorStatus;
import com.example.helloworldmvc.converter.CommunityConverter;
import com.example.helloworldmvc.domain.Community;
import com.example.helloworldmvc.domain.File;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.repository.CommunityRepository;
import com.example.helloworldmvc.repository.UserRepository;
import com.example.helloworldmvc.repository.UuidRepository;
import com.example.helloworldmvc.web.dto.CommunityRequestDTO;
import com.example.helloworldmvc.web.dto.CommunityResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
@Transactional
public class CommunityServiceImpl implements CommunityService {

    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final UuidRepository uuidRepository;
    private final S3Service s3Service;
    @Override
    public CommunityResponseDTO.CreatedPostDTO createCommunityPost(String userId, Long categoryId, CommunityRequestDTO.CreatePostDTO request) {
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Community community = CommunityConverter.toCommunityPost(request, categoryId);
        community.setUser(user);
        if(request.getImages() != null) {
            for (MultipartFile image : request.getImages()) {
                File file = s3Service.setCommunityImage(image, community);
            }
        }
        return CommunityConverter.toCreatedPostDTO(communityRepository.save(community));
    }
}
