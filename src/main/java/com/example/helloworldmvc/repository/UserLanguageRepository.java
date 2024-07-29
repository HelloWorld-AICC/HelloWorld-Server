package com.example.helloworldmvc.repository;

import com.example.helloworldmvc.domain.mapping.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
}
