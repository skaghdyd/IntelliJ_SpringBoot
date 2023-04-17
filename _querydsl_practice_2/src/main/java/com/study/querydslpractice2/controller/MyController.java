package com.study.querydslpractice2.controller;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydslpractice2.entity.Emp;
import com.study.querydslpractice2.entity.QEmp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {
    private final JPAQueryFactory jpaQueryFactory;
    private final JPAQuery jpaQuery;

    @GetMapping("/test")
    @ResponseBody
    public List<Emp> test() {
        QEmp emp = QEmp.emp;
        List<Emp> list = jpaQueryFactory.selectFrom(emp).fetch();
        System.out.println(list);
        return list;
    }

    @GetMapping("/test2")
    @ResponseBody
    public List<Emp> test2() {
        QEmp emp = QEmp.emp;
        List<Emp> list = jpaQuery.select(emp.empno).from(emp).fetch();
        System.out.println(list);
        return list;
    }
}
