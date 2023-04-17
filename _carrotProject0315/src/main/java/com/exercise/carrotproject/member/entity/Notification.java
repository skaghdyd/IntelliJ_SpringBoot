package com.exercise.carrotproject.member.entity;

import com.exercise.carrotproject.category.NotiKind;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notiId;

    @ManyToOne
    @JoinColumn(name = "mem_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private NotiKind notiKind;

    private String notiContent;
    private Integer readState;
    private Timestamp notiDate;
}
