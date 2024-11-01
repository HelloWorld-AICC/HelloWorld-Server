package com.example.helloworldmvc.repository;

import com.example.helloworldmvc.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {

}
