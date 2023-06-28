package com.myproject.plogging.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name ="meetings")
@NoArgsConstructor(access = PROTECTED)
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meeting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_no")
    private User user;

    private String title;

    private String location;

    private String period;

    @Column(name="max_count")
    private int maxCount;


    @Column(length = 1000)
    private String contents;
    private Long two;
    private Long three;
    private Long four;

    @OneToMany(mappedBy = "meeting")
    private List<BeforeList> beforeLists = new ArrayList<>();


    @OneToMany(mappedBy = "meeting")
    private List<Chatting> chattingList = new ArrayList<>();



}
