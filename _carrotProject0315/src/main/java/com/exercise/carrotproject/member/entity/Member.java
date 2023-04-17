package com.exercise.carrotproject.member;

import javax.persistence.*;

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
}
