package com.study.querydslpractice2.repository;

import com.study.querydslpractice2.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface empRepository extends JpaRepository<Emp, Long> {

}
