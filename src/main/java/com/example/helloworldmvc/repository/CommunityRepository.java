package com.example.helloworldmvc.repository;

import com.example.helloworldmvc.domain.Community;
import com.example.helloworldmvc.domain.User;
import com.example.helloworldmvc.domain.enums.CommunityCategory;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Page<Community> findAllByCommunityCategoryOrderByCreatedAtDesc(CommunityCategory category, Pageable pageable);

//    Page<Community> findAllByUserId(Long userId, Pageable pageable);
    @EntityGraph(attributePaths = {"user"})
    Page<Community> findAllByUserId(@Param("userId") Long userId, Pageable pageable);
}
