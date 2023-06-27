package com.myproject.plogging.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Marker {

    @Id
    @GeneratedValue
    @Column(name = "marker_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_no")
    private User user;

    private Integer lotd;

    private Integer latd;
}
