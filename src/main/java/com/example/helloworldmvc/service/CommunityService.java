package com.example.helloworldmvc.service;

import com.example.helloworldmvc.web.dto.CommunityRequestDTO;
import com.example.helloworldmvc.web.dto.CommunityResponseDTO;

public interface CommunityService {

    public CommunityResponseDTO.CreatedPostDTO createCommunityPost(String userId, Long categoryId, CommunityRequestDTO.CreatePostDTO request);
}
