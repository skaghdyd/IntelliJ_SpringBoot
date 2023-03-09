package com.study.springboot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
public class MovieDto {
	private Long did;
	private String title;
	private String content;
	private int idx;
	private GenreDto genreDto;
}
