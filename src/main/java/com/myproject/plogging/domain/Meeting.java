package com.myproject.plogging.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name ="meetings")
@NoArgsConstructor(access = PROTECTED)
@Getter
@AllArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meeting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_no")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

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

    @JsonIgnore
    @OneToMany(mappedBy = "meeting")
    private List<BeforeList> beforeLists = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "meeting")
    private List<Chatting> chattingList = new ArrayList<>();

    public void enjoyMeeting(Long id){
        if(two == null) this.two = id;
        else if(three == null) this.three = id;
        else if(four == null) this.four = id;
    }

}
