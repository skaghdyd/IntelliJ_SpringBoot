package com.exercise.carrotproject.member.entity;

import com.exercise.carrotproject.category.Loc;
import com.exercise.carrotproject.chat.entity.ChatRoom;
import com.exercise.carrotproject.post.entity.Post;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "mem_id")
    private String memId;

    @Column(name = "mem_pwd")
    private String memPwd;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "prof_path")
    private String profPath;

    @Column(name = "manner_score")
    private Double mannerScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "loc")
    private Loc loc;

    @OneToMany(mappedBy = "seller")
    private List<Post> postList;

    @OneToMany(mappedBy = "seller")
    private List<ChatRoom> roomListBySeller;

    @OneToMany(mappedBy = "buyer")
    private List<ChatRoom> roomListByBuyer;

    @OneToMany(mappedBy = "member")
    private List<Wish> wishList;

    @OneToMany(mappedBy = "seller")
    private List<SellList> sellListsBySeller;

    @OneToMany(mappedBy = "buyer")
    private List<SellList> sellListsByBuyer;

    @OneToMany(mappedBy = "seller")
    private List<BuyList> buyListsBySeller;

    @OneToMany(mappedBy = "buyer")
    private List<BuyList> buyListsByBuyer;

    @OneToMany(mappedBy = "member")
    private List<Keyword> keywordList;

    @OneToMany(mappedBy = "member")
    private List<Notification> notificationList;
}
