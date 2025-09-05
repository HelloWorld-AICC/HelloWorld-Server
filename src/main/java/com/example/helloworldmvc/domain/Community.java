package com.example.helloworldmvc.domain;

import com.example.helloworldmvc.domain.common.BaseEntity;
import com.example.helloworldmvc.domain.enums.CommunityCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Community extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'ETC'")
    private CommunityCategory communityCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    @BatchSize(size = 20)
    private List<File> fileList = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    @BatchSize(size = 20)
    private List<Comment> commentList = new ArrayList<>();

    public void setUser(User user) {
        if(this.user != null){
            user.getCommunityList().remove(this);
        }
        this.user = user;
        user.getCommunityList().add(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCommunityCategory(CommunityCategory communityCategory) {
        this.communityCategory = communityCategory;
    }
}
