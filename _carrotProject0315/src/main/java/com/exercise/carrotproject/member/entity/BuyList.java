package com.exercise.carrotproject.member.entity;

import com.exercise.carrotproject.post.entity.Post;

import javax.persistence.*;

@Entity
public class BuyList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buy_id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Member seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Member buyer;
}
