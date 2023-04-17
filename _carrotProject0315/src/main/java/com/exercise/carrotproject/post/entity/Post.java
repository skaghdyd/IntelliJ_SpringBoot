package com.exercise.carrotproject.post.entity;

import com.exercise.carrotproject.category.Category;
import com.exercise.carrotproject.category.Loc;
import com.exercise.carrotproject.chat.entity.ChatRoom;
import com.exercise.carrotproject.member.entity.BuyList;
import com.exercise.carrotproject.member.entity.Member;
import com.exercise.carrotproject.member.entity.SellList;
import com.exercise.carrotproject.member.entity.Wish;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Member seller;

    @Column(name = "price")
    private Integer price;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "loc")
    private Loc loc;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "hide_state")
    private Integer hideState;

    @Column(name = "sell_state")
    private Integer sellState;

    @Column(name = "hits")
    private Integer hits;

    @OneToMany(mappedBy = "post")
    private List<ChatRoom> roomList;

    @OneToMany(mappedBy = "post")
    private List<PostImg> postImgList;

    @OneToMany(mappedBy = "post")
    private List<Wish> wishList;

    @OneToMany(mappedBy = "post")
    private List<SellList> sellLists;

    @OneToMany(mappedBy = "post")
    private List<BuyList> buyLists;
}
