package com.fromis.fromis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;

    private String title;

    private String content;

    private Integer hit;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime writeday;

    private String id;

    private String filename;

    private String filepath;

    @PrePersist
    protected void onCreate(){
        writeday=LocalDateTime.now();
    }
}

