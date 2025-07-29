package com.example.helloworldmvc.converter;

import com.example.helloworldmvc.domain.Comment;
import com.example.helloworldmvc.domain.Community;
import com.example.helloworldmvc.domain.enums.CommunityCategory;
import com.example.helloworldmvc.web.dto.CommunityRequestDTO;
import com.example.helloworldmvc.web.dto.CommunityResponseDTO;
import com.example.helloworldmvc.web.dto.FileDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static CommunityResponseDTO.PostListDTO toPostListDTO(Page<Community> postList, Long categoryId){
        List<CommunityResponseDTO.PostDTO> posts = postList.stream().map(i -> CommunityConverter.toPostDTO(i, categoryId)).toList();
        return CommunityResponseDTO.PostListDTO.builder()
                .postDTOList(posts)
                .build();
    }

    public static CommunityResponseDTO.PostDTO toPostDTO(Community community, Long categoryId){
        String imageUrl = null;
        if(!community.getFileList().isEmpty()){
            imageUrl = community.getFileList().get(0).getUrl();
        }
        return CommunityResponseDTO.PostDTO.builder()
                .post_id(community.getId())
                .title(community.getTitle())
                .created_at(community.getCreatedAt())
                .commentNum(community.getCommentList().size())
                .imageUrl(imageUrl)
                .content(community.getContent())
                .category_id(categoryId)
                .build();
    }

    public static CommunityResponseDTO.PostDetailDTO toPostDetailDTO(Community community, Page<Comment> commentList){
        List<FileDTO.FileDetailRes> list = new ArrayList<FileDTO.FileDetailRes>();
        if(!community.getFileList().isEmpty()){
            community.getFileList().stream().forEach(file -> {
                list.add(FileConverter.toFileDetailRes(file.getUrl(), file.getFileType()));
            });
        }
        List<CommunityResponseDTO.CommentDTO> comments = commentList.stream().map(CommunityConverter::toCommentDTO).toList();
        return CommunityResponseDTO.PostDetailDTO.builder()
                .title(community.getTitle())
                .content(community.getContent())
                .communityWriterEmail(community.getUser().getEmail())
                .created_at(community.getCreatedAt())
                .fileList(list)
                .commentDTOList(comments)
                .build();
    }

    public static CommunityResponseDTO.CommentDTO toCommentDTO(Comment comment){
        return CommunityResponseDTO.CommentDTO.builder()
                .anonymousName(comment.getAnonymous())
                .commentWriterEmail(comment.getUser().getEmail())
                .created_at(comment.getCreatedAt())
                .content(comment.getContent())
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

    public static Long toCategoryId(CommunityCategory category) {
        switch (category) {
            case WORRY:
                return 0L;
            case MEDICAL:
                return 1L;
            case QUALIFICATION:
                return 2L;
            default:
                return 3L;
        }
    }

}
