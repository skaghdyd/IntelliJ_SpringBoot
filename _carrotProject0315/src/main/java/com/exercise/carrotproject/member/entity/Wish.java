package com.exercise.carrotproject.member.entity;

import com.exercise.carrotproject.post.entity.Post;

import javax.persistence.*;

@Entity
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "mem_id")
    private Member member;
}
