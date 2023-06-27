package com.myproject.plogging.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="meetings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="meeting_id")
    private Long id;

    private Long creator;

    private String title;

    private String location;

    private String period;

    @Column(name="max_count")
    private int maxCount;


    @Column(length = 1000)
    private String contents;

    private Long one;
    private Long two;
    private Long three;
    private Long four;

}
