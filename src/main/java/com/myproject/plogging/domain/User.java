package com.myproject.plogging.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_no")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private String username;

    public void setPassword(String password) {
        this.password = password;
    }

    private String nickname;

    private String password;

    private String email;

    private String phone;

    private String address;

    @Column(columnDefinition = "integer default 0")
    private Integer plasticBagCount;

    @Builder
    public User(String userId, String username, String nickname, String password, String email, String phone, String address) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.password =  password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.plasticBagCount = 0;
    }

    public void updateNickname(String nickname){this.nickname = nickname;}
    public void updatePassword(String password){ this.password = password;}
    public void updateEmail(String email){ this.email = email;}
    public void updatePhone(String nickname){ this.phone = phone;}
    public void updateAddress(String address){ this.address = address;}

    public void incrementPlasticBagCount(){
        this.plasticBagCount +=1;
    }


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Meeting> meetingList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<PhotoList> photoList =  new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Marker> markerList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<BeforeList> beforeList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ChatText> chatTextsList = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Authorities> authList = new ArrayList<>();


}
