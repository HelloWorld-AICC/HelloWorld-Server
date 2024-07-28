package com.example.helloworldmvc.repository;

import com.example.helloworldmvc.domain.Center;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<Center, Long> {
    Page<Center> findAll(Pageable pageable);
}
