package com.fromis.fromis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;

    private String title;

    private String content;

    private String name;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime writeday;

    private Integer hit;

    private String filename;

    private String filepath;

    @PrePersist
    protected void onCreate(){
        writeday=LocalDateTime.now();
    }
}
