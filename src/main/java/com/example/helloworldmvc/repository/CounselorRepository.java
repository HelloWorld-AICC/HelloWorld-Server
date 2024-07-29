package com.example.helloworldmvc.repository;

import com.example.helloworldmvc.domain.Counselor;
import com.example.helloworldmvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {
}
