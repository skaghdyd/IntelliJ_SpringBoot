package com.exercise.carrotproject.member.entity;

import javax.persistence.*;

@Entity
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

    private String keywordContent;

    @ManyToOne
    @JoinColumn(name = "mem_id")
    private Member member;
}
