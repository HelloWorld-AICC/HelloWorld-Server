package com.example.helloworldmvc.repository;

import com.example.helloworldmvc.domain.Counselor;
import com.example.helloworldmvc.domain.mapping.CounselorLanguage;
import com.example.helloworldmvc.domain.mapping.UserLanguage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {
    @Query("SELECT DISTINCT c FROM Counselor c JOIN c.counselorLanguageList l WHERE l.id IN :userLanguageList")
    Page<Counselor> findAllByCounselorLanguageList(List<Long> userLanguageList, Pageable pageable);
}
