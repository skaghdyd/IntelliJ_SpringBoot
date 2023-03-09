package com.study.springboot.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class MoviePK implements Serializable{
	private Integer type;
	private Integer idx;
}
