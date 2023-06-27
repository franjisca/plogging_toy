package com.myproject.plogging.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeforeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "before_id")
    private Long id;

    @Column(name = "meeting_id")
    private Long meetingId;

    @Column(name = "user_id")
    private Long userId;


}
