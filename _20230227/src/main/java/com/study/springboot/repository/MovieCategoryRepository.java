package com.study.springboot.repository;

import com.study.springboot.entity.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MovieCategoryRepository
	extends JpaRepository<MovieCategory, Integer>{

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO movie_category (name)\r\n"
			+ "VALUES (:name)", nativeQuery = true)
	void insertMovieCategory(@Param("name") String name);
}