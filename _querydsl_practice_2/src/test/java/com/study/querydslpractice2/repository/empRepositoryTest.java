package com.study.querydslpractice2.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.querydslpractice2.entity.Emp;
import com.study.querydslpractice2.entity.QEmp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
class empRepositoryTest {
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    EntityManager em;

    @Test
    public void test1() {
        QEmp emp = QEmp.emp;
        jpaQueryFactory = new JPAQueryFactory(em);
        List<Emp> list = jpaQueryFactory.selectFrom(emp).fetch();
//        List<Tuple> list = jpaQueryFactory
//                .select(emp.ename, emp.job, emp.sal)
//                .from(emp)
//                .fetch();
        list.stream().forEach(t->{
            System.out.println(t);
        });
    }

    @Test
    public void test2() {
        QEmp emp = QEmp.emp;
        jpaQueryFactory = new JPAQueryFactory(em);
        String ename = "%B%";
        String empno = "%9";
        BooleanBuilder builder = new BooleanBuilder();
        if (ename != null) {
            builder.and(emp.ename.like(ename));
        }
        if (empno != null) {
//            builder.and(emp.empno.like(empno));
            builder.or(emp.empno.like(empno));
        }
//        List<Tuple> list = jpaQueryFactory.select(emp.empno, emp.ename, emp.job, emp.sal, emp.comm)
        List<Tuple> list = jpaQueryFactory.select(emp.empno, emp.ename, emp.job, emp.sal, emp.comm.coalesce(0).as("comm"))
                .from(emp)
                .where(builder)
                .fetch();
        list.stream().forEach(t->{
            System.out.println(t);
        });
    }

    @Test
    public void test3() {
        QEmp emp = QEmp.emp;
        jpaQueryFactory = new JPAQueryFactory(em);
        JPAQuery<Long> count = jpaQueryFactory.select(Wildcard.count).from(emp);
        System.out.println("count >>> " + count); // 쿼리문이 찍힘
        List<Long> fetch = count.fetch();
        System.out.println("fetch.get(0); >>> " + fetch.get(0)); //결과값

        JPAQuery<Long> query =new JPAQuery<>(em);
        JPAQuery<Long> from = query.select(Wildcard.count).from(emp);
        System.out.println("from >>> " + from); // 쿼리문이 찍힘
        Long result = from.fetchOne();
        System.out.println("result >>> " + result); // 결과값

        List<Long> list = jpaQueryFactory.select(Wildcard.count).from(emp).fetch();
//        JPAQuery<Long> count = (JPAQuery<Long>) list;
        list.stream().forEach(t->{
            System.out.println(t);
        });

    }
}