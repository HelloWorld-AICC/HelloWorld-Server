package com.example.helloworldmvc.repository;

import com.example.helloworldmvc.domain.Comment;
import com.example.helloworldmvc.domain.Community;
import com.example.helloworldmvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserAndCommunity(User user, Community community);

    @Query("SELECT COALESCE(MAX(c.anonymous), 0) FROM Comment c WHERE c.community = :community")
    Long findMaxAnonymousInCommunity(Community community);
}

