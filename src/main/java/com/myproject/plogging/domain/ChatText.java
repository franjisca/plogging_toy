package com.myproject.plogging.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatText {

    @Id
    @GeneratedValue
    @Column(name = "text_id")
    private Long id;

    private Long writer;


    @Column(name = "chatting_text")
    private String messages;

    @Column(name="meeting_id")
    private Long meetingId;


    @CreationTimestamp
    private LocalDateTime date;

}
