package com.study.springboot.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@Entity
public class Emp {
    @Id
    private Long empno;
    private String ename;
    private String job;
    private Integer mgr;
    private String hiredate;
    private int sal;

    private Integer comm;
    private int deptno;


}