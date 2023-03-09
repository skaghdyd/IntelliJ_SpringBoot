package com.study.springboot.entity;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
@Log4j2
public class MyEntityManager {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public List<Person> create(){
        List list = new ArrayList();
        IntStream.rangeClosed(1, 10).forEach(t->{
            Person person = Person.builder()
                    .name("홍"+t)
                    .addr("마포"+t)
                    .build();
            em.persist(person);
            list.add(person);
        });
        return list;
    }

    @Transactional
    public Person update(){
//        Person person = em.find(Person.class, 1L);
//        person.setAddr("성남");

        Person person = Person.builder()
                .id(1L)
                .name("수정")
                .build();
        log.info(em.contains(person));
        person = em.merge(person);
        log.info(em.contains(person));
        return person;
    }

    public List<Person> selectAll(){
        String sql = "select p from Person p";
        List<Person> list = em.createQuery(sql, Person.class).getResultList();
        list.stream().forEach(person -> {
            log.info(em.contains(person));
        });
        return list;
    }

    @Transactional
    public List<Person> delete(){
        Person person = Person.builder()
                .id(1L)
                .build();

        log.info(em.contains(person)); //false

        em.merge(person);
        log.info(em.contains(person)); //true -> false

//        em.persist(person);//detatch 상태라서 persist 안됨.
//        log.info(em.contains(person));

        em.remove(person);

//        String sql = "select p from Person p";
//        List<Person> list = em.createQuery(sql, Person.class).getResultList();

        return null;
    }
}
