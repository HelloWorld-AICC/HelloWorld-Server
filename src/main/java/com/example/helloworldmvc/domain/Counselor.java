package com.example.helloworldmvc.domain;

import com.example.helloworldmvc.domain.enums.UserLanguage;
import com.example.helloworldmvc.domain.mapping.AvailableLanguage;
import com.example.helloworldmvc.domain.mapping.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Counselor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;


    @Column(nullable = true)
    private LocalDateTime start;

    @Column(nullable = true)
    private LocalDateTime end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "center_id")
    private Center center;

    @OneToMany(mappedBy = "counselor")
    private List<AvailableLanguage> availableLanguageList = new ArrayList<>();
}
