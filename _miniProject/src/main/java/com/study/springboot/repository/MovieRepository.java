package com.study.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springboot.entity.Movie;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
