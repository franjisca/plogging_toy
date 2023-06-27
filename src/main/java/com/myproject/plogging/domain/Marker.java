package com.myproject.plogging.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Marker {

    @Id
    @GeneratedValue
    @Column(name = "marker_id")
    private Long id;

    private Long userId;

    private Integer lotd;

    private Integer latd;
}
