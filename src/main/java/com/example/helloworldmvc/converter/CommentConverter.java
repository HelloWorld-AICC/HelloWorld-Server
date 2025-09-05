package com.example.helloworldmvc.converter;

import com.example.helloworldmvc.domain.Comment;
import com.example.helloworldmvc.domain.Community;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.web.dto.CommentRequestDTO;
import com.example.helloworldmvc.web.dto.CommentResponseDTO;

public class CommentConverter {

    public static Comment toComment(CommentRequestDTO.commentCreateReq request, Community community, User user, Long anonymous) {
        return Comment.builder()
                .user(user)
                .community(community)
                .anonymous(anonymous)
                .content(request.getContent())
                .build();
    }

    public static CommentResponseDTO.commentCreateRes toCommentCreateRes(Long commentId, Long communityId) {
        return CommentResponseDTO.commentCreateRes.builder()
                .commentId(commentId)
                .communityId(communityId)
                .build();
    }
}
