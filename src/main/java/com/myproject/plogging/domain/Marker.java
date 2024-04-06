package com.myproject.plogging.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Marker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marker_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="user_no")
    private User user;

    private String lotd;

    private String latd;


    @Builder
    public Marker(User user, String lotd, String latd) {
        this.user = user;
        this.lotd = lotd;
        this.latd = latd;
    }
}
