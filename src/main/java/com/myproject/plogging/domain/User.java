package com.myproject.plogging.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String phone;

    private String address;

    @CreationTimestamp
    @Column(name = "enjoy_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime enjoyDate;


    @Builder
    public User(String userId, String username, String nickname, String password, String email, String phone, String address) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
