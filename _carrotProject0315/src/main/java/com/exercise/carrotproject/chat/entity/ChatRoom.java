package com.exercise.carrotproject.chat;

import com.exercise.carrotproject.member.Member;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "mem_id")
    @Column(name = "seller_id")
    private List<Member> sellerId;

    @ManyToOne
    @JoinColumn(name = "mem_id")
    @Column(name = "buyer_id")
    private List<Member> buyerId;

}
