package com.exercise.carrotproject.post.entity;

import javax.persistence.*;

@Entity
public class PostImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String orgName;
    private String savedName;
    private String savedPath;
}
