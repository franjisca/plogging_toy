package com.myproject.plogging.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue
    @Column(name ="user_no")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String phone;

    private String address;

    //@CreationTimestamp
    //@Column(name = "signup_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    //private LocalDateTime signupDate;


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

    @OneToMany(mappedBy = "user")
    private List<Meeting> meetingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PhotoList> photoList =  new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Marker> markerList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BeforeList> beforeLists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ChatText> chatTextsList = new ArrayList<>();


}
