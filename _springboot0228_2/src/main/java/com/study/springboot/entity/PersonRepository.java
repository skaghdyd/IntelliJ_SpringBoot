package com.study.springboot.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findPersonByName(String name);
    List<Person> findAllByNameLike(String name);

    @Query(value="select * from person where name like %:name%", nativeQuery = true)
    List<Person> selectAll(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "insert into person(name, addr) values(:name, :addr)", nativeQuery = true)
    void insertPerson(@Param("name") String name, @Param("addr") String addr);

    @Transactional
    @Modifying
    @Query(value = "insert into person(name, addr) values(:#{#person.name}, :#{#person.addr})", nativeQuery = true)
    void insertPerson2(@Param("person") Person person);
}
