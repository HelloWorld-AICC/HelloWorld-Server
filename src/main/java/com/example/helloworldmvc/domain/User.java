package com.example.helloworldmvc.domain;

import com.example.helloworldmvc.domain.common.BaseEntity;
import com.example.helloworldmvc.domain.enums.UserLanguage;
import com.example.helloworldmvc.domain.mapping.Reservation;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ENGLISH'")
    private UserLanguage language;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Summary> userSummaryList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private File file;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();
}

