package com.myproject.plogging.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chattings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chatting_id")
    private Long id;

    @Column(name = "meeting_id")
    private Long meetingId;


}
