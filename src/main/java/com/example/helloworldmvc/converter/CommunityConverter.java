package com.example.helloworldmvc.converter;

import com.example.helloworldmvc.domain.Community;
import com.example.helloworldmvc.domain.enums.CommunityCategory;
import com.example.helloworldmvc.web.dto.CommunityRequestDTO;
import com.example.helloworldmvc.web.dto.CommunityResponseDTO;

import java.util.ArrayList;

public class CommunityConverter {
    public static Community toCommunityPost(CommunityRequestDTO.CreatePostDTO createPostDTO, Long categoryId){
        CommunityCategory communityCategory = toCommunityCategory(categoryId);
        return Community.builder()
                .title(createPostDTO.getTitle())
                .content(createPostDTO.getContent())
                .communityCategory(communityCategory)
                .fileList(new ArrayList<>())
                .build();
    }
    public static CommunityResponseDTO.CreatedPostDTO toCreatedPostDTO(Community community){
        return CommunityResponseDTO.CreatedPostDTO.builder()
                .community_id(community.getId())
                .build();
    }

    public static CommunityCategory toCommunityCategory(Long categoryId){
        CommunityCategory communityCategory = null;
        switch (categoryId.intValue()){
            case 0:
                communityCategory = CommunityCategory.WORRY;
                break;
            case 1:
                communityCategory = CommunityCategory.MEDICAL;
                break;
            case 2:
                communityCategory = CommunityCategory.QUALIFICATION;
                break;
            default:
                communityCategory = CommunityCategory.ETC;
                break;
        }
        return communityCategory;
    }
}
