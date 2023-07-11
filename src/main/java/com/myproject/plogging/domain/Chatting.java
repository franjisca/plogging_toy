package com.myproject.plogging.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chattings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chatting_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @JsonIgnore
    @OneToMany(mappedBy = "chatting")
    private List<ChatText> textList = new ArrayList<>();

    public Chatting(Meeting meeting) {
        this.meeting = meeting;
    }
}
