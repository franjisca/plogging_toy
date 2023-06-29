package com.myproject.plogging.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PhotoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    private String image;

    public void setImage(String image) {
        this.image = image;
    }

    @Column(columnDefinition = "integer default 0")
    private Integer likes;

    public void addLikesCount(){
        this.likes = likes++;

    }

    @JsonIgnore
    @Column(name="upload_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime uploadDate;

    @JsonIgnore
    @Column(name ="modify_date")
    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    public PhotoList(User user, String image) {
        this.likes = 0;
        this.user = user;
        this.image = image;
    }
}
