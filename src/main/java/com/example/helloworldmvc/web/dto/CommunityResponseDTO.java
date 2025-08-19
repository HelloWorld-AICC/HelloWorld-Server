package com.example.helloworldmvc.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class CommunityResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatedPostDTO{
        Long community_id;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostListDTO{
        List<PostDTO> postDTOList;
    }
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDTO{
        Long post_id;
        String title;
        LocalDateTime created_at;
        Integer commentNum;
        String imageUrl;
        String content;
        Long category_id;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDetailDTO{
        String title;
        String content;
        LocalDateTime created_at;
        List<FileDTO.FileDetailRes> fileList;
        List<CommentDTO> commentDTOList;
        Boolean isOwner;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentDTO{
        Long anonymousName;
        Long commentId;
        String commentWriterEmail;
        LocalDateTime created_at;
        String content;
        Boolean isOwner;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeletedPostDTO{
        String categoryName;
        Long post_id;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyPostDTO{
        String title;
        String content;
        String communityWriterEmail;
        LocalDateTime created_at;
        Boolean isOwner;
    }
}
