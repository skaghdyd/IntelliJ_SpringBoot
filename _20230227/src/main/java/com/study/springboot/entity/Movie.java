package com.study.springboot.entity;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@DynamicInsert
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	@EmbeddedId
	MoviePK moviePK;

	private Integer groupIdx;
	@ColumnDefault("1")
	private Integer level;
	@ColumnDefault("1")
	private Integer levelIdx;
	private String title;
	private String content;

	@MapsId("type")
	@ManyToOne(targetEntity = MovieCategory.class)
	@JoinColumn(name="movieCategory_type")
	public MovieCategory movieCategory;
}
