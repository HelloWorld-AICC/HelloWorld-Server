package com.example.helloworldmvc.repository;

import com.example.helloworldmvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
