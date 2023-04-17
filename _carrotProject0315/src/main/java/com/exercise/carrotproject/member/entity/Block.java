package com.exercise.carrotproject.member.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockId;

    @ManyToOne
    @JoinColumn(name = "from_mem")
    private Member fromMem;

    @ManyToOne
    @JoinColumn(name = "to_mem")
    private Member toMem;

    private Timestamp blockDate;


}
