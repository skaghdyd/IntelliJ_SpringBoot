package com.study.springboot.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString(exclude = "movieList")
public class MovieCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieCategory_type")
	private Integer type; //영화종류
	
	private String name;

	@OneToMany(mappedBy = "movieCategory")
	public List<Movie> movieList = new ArrayList<>();
}
