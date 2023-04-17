package com.study.springboot.repositroy;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.springboot.dto.TableDto;
import com.study.springboot.entity.Emp;
import com.study.springboot.entity.QEmp;
import com.study.springboot.entity.QTables;
import com.study.springboot.entity.Tables;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TablesRepositoryTest {

    @Autowired
    TablesRepository tablesRepository;
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    EmpRepository empRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void test1() {
        QTables tt = QTables.tables;
        Tables tables = entityManager.find(Tables.class, 1L);
        System.out.println(tables);

        jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Long> count = jpaQueryFactory.select(Wildcard.count).from(QTables.tables);
        System.out.println(count.stream().count());
        QTables user = QTables.tables;
        List<Tables> names = jpaQueryFactory.select(user)
                .from(user)
                .where(user.tid.between(3, 7))
                .orderBy(user.name.asc())
                .fetch();
        System.out.println(names);

        QTables user1 = QTables.tables;
        String name = "abc4";
        String number = "56";
        BooleanBuilder builder = new BooleanBuilder();
        if (name != null) {
            builder.and(user.name.eq(name));
        }
        if (number != null) {
            builder.and(user.number.eq(number));
        }
        List<Tables> users = jpaQueryFactory.selectFrom(user)
                .where(builder)
                .fetch();
        System.out.println(users);
    }

    @Test
    public void test2() {
        QEmp emp = QEmp.emp;
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        List<Tuple> members = jpaQueryFactory
                .select(emp.empno, emp.ename, emp.sal)
                .from(emp)
                //.where(emp.empno.eq(7654L))
                .fetch();
        System.out.println(members);
    }

    @Test
    public void test3() {
        QEmp emp = QEmp.emp;
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        List<Long> count = jpaQueryFactory.select(Wildcard.count).from(emp).fetch();
        System.out.println(count.get(0));
    }

    @Test
    public void test4() {
        QEmp emp = QEmp.emp;
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        List<Tuple> list = jpaQueryFactory.select(emp.empno, emp.ename, emp.sal ).from(emp).orderBy(emp.empno.asc()).fetch();
        list.stream().forEach(t->{
            System.out.println(t);
        });
    }

    @Test
    public void test5() {
        QEmp emp = QEmp.emp;
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        String ename = "%A%";
        String empno = "%7%";
        BooleanBuilder builder = new BooleanBuilder();
        if (ename != null) {
            builder.and(emp.ename.like(ename));
        }
        if (empno != null) {
            builder.and(emp.empno.like(empno));
        }
        List<Tuple> list = jpaQueryFactory.select(emp.empno, emp.comm.coalesce(0).as("comm")).from(emp).where(builder).fetch();
        System.out.println(list);
    }

    @Test
    public void test6() {
        QEmp emp = QEmp.emp;
        jpaQueryFactory = new JPAQueryFactory(entityManager);
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = emp.ename.contains("E");
        builder.and(expression);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("empno"));
        Page<Emp> list = empRepository.findAll(builder, pageable);
        list.stream().forEach(t->{
            System.out.println(t);
        });
    }
}