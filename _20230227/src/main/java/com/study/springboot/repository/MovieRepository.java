package com.study.springboot.repository;

import com.study.springboot.entity.Movie;
import com.study.springboot.entity.MoviePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository 
	extends JpaRepository<Movie, MoviePK>{

	@Query(value="SELECT ifnull(MAX(idx),0)+1 AS idx_max\r\n"
			+ "FROM movie\r\n"
			+ "WHERE movie_category_type = :type", nativeQuery = true)
	public int findMaxIdxByType(@Param("type") int type);

	@Query(value="SELECT group_idx, level, level_idx, movie_category_type, idx, content, title\r\n"
			+ "FROM movie\r\n"
			+ "WHERE movie_category_type = :type\r\n"
			+ "ORDER BY movie_category_type, idx", nativeQuery = true)
	public List<Movie> selectByType(@Param("type") int type);

//	@Transactional
//	@Modifying
	@Query(value="UPDATE movie\r\n" +
			"SET title = :title\r\n" +
			"\t,content = :content\r\n" +
			"WHERE movie_category_type = :type\r\n" +
			"AND idx = :idx", nativeQuery = true)
	void updateByTypeAndIdx(@Param("type") int type,
							@Param("idx") int idx,
							@Param("title") String title,
							@Param("content") String content);

	@Query(value = "update movie_category set name = :name where type = :type", nativeQuery = true)
	void updateByTypeAndName(@Param("type") int type, @Param("name") int name);

	@Query(value = "select ifnull(MAX(level_idx),0)+1 AS level_idx_max " +
			"from movie " +
			"where movie_category_type = :type " +
			"and group_idx = :idx " +
			"and level = :level", nativeQuery = true)
	int findMaxLevelIdxByTypeAndIdxAndLevel(int type, int idx, Integer level);
}