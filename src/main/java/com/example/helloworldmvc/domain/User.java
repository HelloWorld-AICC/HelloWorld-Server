package com.example.helloworldmvc.domain;

import com.example.helloworldmvc.domain.common.BaseEntity;
import com.example.helloworldmvc.domain.enums.Role;
import com.example.helloworldmvc.domain.mapping.UserLanguage;
import com.example.helloworldmvc.domain.mapping.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private String name;

    @Column(nullable = false, length = 30)
    private String email;

    @Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
    @NotNull
    private Role role;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Summary> userSummaryList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private File file;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLanguage> userLanguageList = new ArrayList<>();

    public User update(String name) {
        this.name = name;

        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }

    public void setFile(File file) {
        this.file = file;
    }

}

