package com.example.helloworldmvc.domain;

import com.example.helloworldmvc.domain.mapping.AvailableLanguage;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 10)
    private String name;

    @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
    private List<AvailableLanguage> availableLanguages = new ArrayList<>();
}
