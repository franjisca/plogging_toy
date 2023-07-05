package com.myproject.plogging.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name ="meetings")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meeting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
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
        if(two == null && two != id) this.two = id;
        else if(three == null && three != id) this.three = id;
        else if(four == null && four != id) this.four = id;
    }


    @Builder
    public Meeting(User user, String title, String location, String period, int maxCount, String contents) {
        this.user = user;
        this.title = title;
        this.location = location;
        this.period = period;
        this.maxCount = maxCount;
        this.contents = contents;
    }
}
